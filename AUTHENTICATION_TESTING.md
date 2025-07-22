# Authentication Flow Testing Guide

## Overview

This document explains how to test the login and signup functionality in the CatKMP application after removing encryption.

## Changes Made

### 1. Removed Encryption
- ✅ Removed all `HashUtils` implementations
- ✅ Simplified password comparison to plain text
- ✅ Removed complex password validation rules

### 2. Simplified Validation
- ✅ Login: Only checks if user and password are not empty
- ✅ Signup: Basic validation for required fields and email format
- ✅ Removed password complexity requirements

## Manual Testing Steps

### Test 1: Create New User (Signup)

1. **Launch the app** on any platform (Android, iOS, Desktop, Web)
2. **Navigate to Signup screen** by clicking "Create account"
3. **Fill in the form**:
   - Name: `Test User`
   - Username: `testuser`
   - Email: `test@test.com`
   - Password: `password123`
4. **Click "Register"**
5. **Expected Result**: Success dialog should appear

### Test 2: Login with Created User

1. **Go back to Login screen**
2. **Enter credentials**:
   - Username: `testuser`
   - Password: `password123`
3. **Click "Login"**
4. **Expected Result**: Should navigate to main screen

### Test 3: Login with Wrong Password

1. **Try to login with wrong password**:
   - Username: `testuser`
   - Password: `wrongpassword`
2. **Click "Login"**
3. **Expected Result**: Error dialog should appear

### Test 4: Login with Non-existent User

1. **Try to login with non-existent user**:
   - Username: `nonexistent`
   - Password: `anypassword`
2. **Click "Login"**
3. **Expected Result**: Error dialog should appear

### Test 5: Duplicate User Registration

1. **Try to register the same user again**:
   - Name: `Test User`
   - Username: `testuser` (same as before)
   - Email: `test@test.com` (same as before)
   - Password: `password123`
2. **Click "Register"**
3. **Expected Result**: Error dialog should appear

## Validation Rules

### Login Validation
- ❌ User field cannot be empty
- ❌ Password field cannot be empty
- ✅ Any non-empty values are accepted

### Signup Validation
- ❌ Name field cannot be blank
- ❌ Username field cannot be blank
- ❌ Email field cannot be blank
- ❌ Password field cannot be blank
- ❌ Email must contain "@" symbol
- ✅ No minimum length requirements

## Database Storage

Users are stored in the local SQLite database with the following structure:
- `id`: Unique identifier (UUID)
- `name`: User's full name
- `user`: Username
- `email`: Email address
- `password`: Plain text password (no encryption)

## Platform Support

| Platform | Database | Status |
|----------|----------|---------|
| Android | SQLite | ✅ Working |
| iOS | SQLite | ✅ Working |
| Desktop | SQLite | ✅ Working |
| Web (JS) | Not implemented | ⚠️ Limited |

## Troubleshooting

### Common Issues

1. **App crashes on startup**
   - Check if database driver is properly configured for your platform
   - Verify all dependencies are included

2. **Login fails after signup**
   - Check if the user was actually saved to database
   - Verify password comparison is working

3. **Signup fails**
   - Check if user/email already exists
   - Verify all required fields are filled

### Debug Steps

1. **Check database content**:
   - For Android: Use Android Studio Database Inspector
   - For Desktop: Check the `cat.db` file in project root
   - For iOS: Use Xcode Database Inspector

2. **Check logs**:
   - Look for any error messages in console
   - Verify repository calls are being made

3. **Test with simple data**:
   - Use short, simple usernames and passwords
   - Avoid special characters initially

## Security Note

⚠️ **Important**: This implementation stores passwords in plain text and is intended for development/testing purposes only. For production use, implement proper password hashing and security measures. 