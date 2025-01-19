@echo off
netsh advfirewall firewall add rule name="My KMP App" dir=in action=allow program="%~dp0\app.exe" enable=yes
