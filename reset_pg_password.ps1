# Script to reset PostgreSQL postgres password to '29082005' and create the 'badminton_shop' database
# IMPORTANT: Run this script as Administrator.

$pgDataDir = "C:\Program Files\PostgreSQL\18\data"
$pgBinDir = "C:\Program Files\PostgreSQL\18\bin"
$pgHba = Join-Path $pgDataDir "pg_hba.conf"
$pgHbaBak = Join-Path $pgDataDir "pg_hba.conf.bak"
$serviceName = "postgresql-x64-18"

Write-Output "=== Starting PostgreSQL Password Reset and DB Setup ==="

# Check Administrator privileges
$currentPrincipal = New-Object Security.Principal.WindowsPrincipal([Security.Principal.WindowsIdentity]::GetCurrent())
if (-not $currentPrincipal.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
    Write-Warning "This script MUST be run as Administrator! Please reopen PowerShell as Administrator and run it again."
    Exit
}

# 1. Backup pg_hba.conf
if (Test-Path $pgHbaBak) {
    Remove-Item $pgHbaBak -Force
}
Copy-Item $pgHba $pgHbaBak -Force
Write-Output "Backed up pg_hba.conf to pg_hba.conf.bak"

# 2. Modify pg_hba.conf to set local connections to trust
$hbaContent = Get-Content $pgHba
$modifiedContent = $hbaContent | ForEach-Object {
    if ($_ -match "^\s*host\s+all\s+all\s+127\.0\.0\.1/32\s+scram-sha-256") {
        "host    all             all             127.0.0.1/32            trust"
    } elseif ($_ -match "^\s*host\s+all\s+all\s+::1/128\s+scram-sha-256") {
        "host    all             all             ::1/128                 trust"
    } else {
        $_
    }
}
$modifiedContent | Set-Content $pgHba -Force
Write-Output "Configured pg_hba.conf for trust authentication temporarily"

# 3. Restart PostgreSQL service
Write-Output "Restarting PostgreSQL service..."
Restart-Service -Name $serviceName -Force

# Wait a few seconds for the service to start fully
Start-Sleep -Seconds 3

# 4. Connect and reset password, and create database
Write-Output "Connecting to PostgreSQL and resetting postgres password..."
$sqlCmds = @(
    "ALTER USER postgres WITH PASSWORD '29082005';",
    "CREATE DATABASE badminton_shop;"
)

$env:PGPASSWORD = ""
foreach ($cmd in $sqlCmds) {
    & (Join-Path $pgBinDir "psql.exe") -U postgres -d postgres -c $cmd 2>$null
}
Write-Output "Password set to '29082005' and database 'badminton_shop' created (if it did not exist)"

# 5. Restore pg_hba.conf
Copy-Item $pgHbaBak $pgHba -Force
Remove-Item $pgHbaBak -Force
Write-Output "Restored pg_hba.conf security settings"

# 6. Restart PostgreSQL service again to enforce scram-sha-256
Write-Output "Restarting PostgreSQL service again..."
Restart-Service -Name $serviceName -Force

Write-Output "=== PostgreSQL Reset and Setup Complete! ==="
