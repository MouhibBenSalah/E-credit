# E-Credit Application: System Architecture and User Roles Overview

## System Architecture
The E-Credit application is built using a **microservices architecture** with:
- **Frontend**: Angular application (port 4200)
- **Backend**: Spring Boot microservices
  - User Service (port 4020)
  - DemandeCredit Service 
  - Gateway Service
  - Discovery Service
  - Config Server
  - Notification Service

## User Roles and Responsibilities

### 1. Client (Non-Account Holders)
- **Status**: Do NOT have accounts in the system
- **Access**: Cannot directly interact with the application
- **Purpose**: End customers who need credit but must go through bank representatives

### 2. Admin (System Administrator)
- **Default Account**: 
  - Email: `admin@ecredit.com`
  - Password: `admin123` (should be changed after first login)
  - CIN: `1000000000`
- **Responsibilities**:
  - Create and manage user accounts for bank staff
  - Access admin dashboard with system statistics
  - Monitor all credit requests and user activities
  - Manage system configuration and security

### 3. CHEF_AGENCE (Agency Manager/Branch Manager)
- **French Term**: "Chef d'Agence"
- **Primary Role**: **Credit Request Decision Maker**
- **Responsibilities**:
  - Review credit requests submitted by Chargé de Banque
  - **Approve** or **Reject** credit applications (`ACCEPTÉE` or `REFUSÉE`)
  - Access loan evaluation tools with automated scoring system
  - Monitor branch performance and statistics

### 4. CHARGE_BANQUE (Bank Officer)
- **French Term**: "Chargé de la Banque"
- **Primary Role**: **Credit Request Creator**
- **Responsibilities**:
  - Meet with clients who need credit
  - Create credit requests (`demande de crédit`) on behalf of clients
  - Gather required documentation and client information
  - Submit requests to Chef d'Agence for approval
  - Handle client communication and documentation

## Credit Request Workflow

### Step 1: Request Creation
```
Client (no account) → meets with → CHARGE_BANQUE → creates → DemandeCredit
```
- Client approaches bank for credit
- CHARGE_BANQUE interviews client and gathers information
- CHARGE_BANQUE creates `DemandeCredit` with status `EN_COURS`

### Step 2: Request Evaluation
```
DemandeCredit (EN_COURS) → reviewed by → CHEF_AGENCE → decision made
```
- CHEF_AGENCE accesses loan evaluation system
- System calculates risk score based on:
  - User financial information (salary, income, charges)
  - Account status and payment history
  - Credit amount and duration
- CHEF_AGENCE makes final decision

### Step 3: Status Update
```
CHEF_AGENCE → updates status → ACCEPTÉE | REFUSÉE
```
- Uses PATCH endpoint: `/DemandeCredit/{id}/status`
- Status options: `ACCEPTÉE`, `REFUSÉE`, `EN_COURS`

## Current Implementation Status

### ✅ Backend Implementation (Complete)
- **User Management**: Fully implemented with all 4 roles
- **Admin Operations**: Account creation, email notifications, role management
- **Credit Request Processing**: Complete CRUD operations and status management
- **Loan Evaluation**: Automated scoring system for decision support
- **Email System**: Account creation notifications with password setup
- **Security**: Role-based access control with JWT authentication

### ⚠️ Frontend Implementation (Partial)
- **Limitations Identified**:
  - Role enum only includes `Client` and `Admin` (missing `CHEF_AGENCE`, `CHARGE_BANQUE`)
  - Guards only check for `Client` and `Admin` roles
  - No specific interfaces for CHEF_AGENCE and CHARGE_BANQUE workflows
  - Missing role-based navigation for new user types

### 🔧 Required Frontend Updates
To complete the system, the frontend needs:

1. **Update Role Enum**:
```typescript
export enum Role {
    Client = 'Client',
    Admin = 'Admin',
    CHEF_AGENCE = 'CHEF_AGENCE',
    CHARGE_BANQUE = 'CHARGE_BANQUE'
}
```

2. **Create New Guards**:
- `chefAgenceGuard` for agency manager access
- `chargeBanqueGuard` for bank officer access

3. **New Components Needed**:
- Chef d'Agence dashboard for reviewing credit requests
- Chargé de Banque interface for creating credit requests
- Credit evaluation interface with scoring system
- Role-based navigation menus

## Data Flow Example

### Typical Credit Request Lifecycle:

1. **Client Consultation**:
   - Client visits bank branch
   - Meets with CHARGE_BANQUE

2. **Request Creation**:
   - CHARGE_BANQUE logs into system
   - Creates new `DemandeCredit` with client information
   - Uploads required documents (`PieceJointe`)
   - Sets status to `EN_COURS`

3. **Review Process**:
   - CHEF_AGENCE receives notification
   - Reviews request details and client financial profile
   - Uses loan evaluation system for risk assessment
   - Makes approval/rejection decision

4. **Decision Implementation**:
   - CHEF_AGENCE updates status to `ACCEPTÉE` or `REFUSÉE`
   - System sends notifications
   - Client is informed of decision

## Security and Access Control

### Current Backend Security:
- `@PreAuthorize("hasRole('Admin')")` for admin operations
- JWT token-based authentication
- Role validation for all endpoints
- Password encryption and temporary password generation

### Email Notification System:
- Automatic emails when admin creates staff accounts
- French language templates
- Password setup links for new users
- Account activation workflow

## Integration Points

### Microservice Communication:
- **User Service** ↔ **DemandeCredit Service**: User validation and credit request management
- **Notification Service**: Email notifications for account creation and status updates
- **Gateway Service**: Unified API entry point with load balancing

This architecture ensures secure, role-based access while maintaining clear separation of concerns between different user types and their responsibilities in the credit approval process.