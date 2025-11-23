# Product Requirement Document (PRD): Splitwise Clone

## 1. Introduction
**Product Name:** FairShare (Tentative)
**Purpose:** To provide a seamless mobile experience for tracking shared expenses and simplifying debt settlement among friends and groups.
**Target Audience:** Roommates, travelers, couples, and friends who frequently share costs.

## 2. Functional Requirements

### 2.1 User Authentication & Profile
*   **FR-001:** Users must be able to sign up and log in using Email/Password.
*   **FR-002:** Users must be able to sign up and log in using Google Authentication (OAuth).
*   **FR-003:** Users must be able to reset their password via email.
*   **FR-004:** Users must be able to edit their profile (Name, Profile Picture, Phone Number).
*   **FR-005:** Users must be able to log out.

### 2.2 Group Management
*   **FR-006:** Users can create a new group with a name and optional cover photo.
*   **FR-007:** Users can add members to a group using their email address or phone number.
*   **FR-008:** Users can generate an invite link to share via other apps.
*   **FR-009:** Users can leave a group (only if their balance in that group is settled).
*   **FR-010:** Users can view a list of all groups they belong to.

### 2.3 Expense Management
*   **FR-011:** Users can add an expense within a group or with an individual friend.
*   **FR-012:** Expense details must include: Amount, Description, Date, Category, Payer(s), and Split details.
*   **FR-013:** Users can support multiple payers for a single expense.
*   **FR-014:** Users can split expenses by:
    *   **Equal:** Default split.
    *   **Exact Amount:** Manually enter amount per person.
    *   **Percentage:** Manually enter percentage per person.
*   **FR-015:** Users can attach an image (receipt) to an expense (Camera or Gallery).
*   **FR-016:** Users can edit or delete an expense. Deletion/Edit should notify all involved parties.

### 2.4 Debt Settlement
*   **FR-017:** Users can view their total balance (positive or negative) across all groups.
*   **FR-018:** Users can view individual balances with specific friends.
*   **FR-019:** Users can "Settle Up" a debt by recording a cash payment.
*   **FR-020:** The system must recalculate balances immediately after an expense is added, edited, or deleted.
*   **FR-021 (Simplify Debts):** The system should optionally optimize debts within a group to minimize transaction count (e.g., if A owes B $10 and B owes C $10, A pays C $10).

### 2.5 Notifications
*   **FR-022:** Users receive push notifications when added to a group.
*   **FR-023:** Users receive push notifications when an expense involving them is added/edited.
*   **FR-024:** Users receive push notifications for settlement reminders.

### 2.6 Localization & Settings
*   **FR-025:** Users must be able to switch the app language between English and Spanish.
*   **FR-026:** Users must be able to select the currency for an expense or group. Supported currencies: US Dollar ($) and Euro (€).
*   **FR-027:** The app should display amounts formatted correctly according to the selected currency and locale.

## 3. Non-Functional Requirements

### 3.1 Performance
*   **NFR-001:** App launch time should be under 2 seconds.
*   **NFR-002:** Expense addition and balance updates should appear to be instantaneous (optimistic UI updates) while syncing in the background.
*   **NFR-003:** The app must function in offline mode. Data should sync when connectivity is restored.

### 3.2 Security
*   **NFR-004:** User passwords must be hashed and salted before storage (handled by Firebase Auth).
*   **NFR-005:** API communication must be over HTTPS.
*   **NFR-006:** Users can only view groups and expenses they are part of.

### 3.3 Usability
*   **NFR-007:** The UI should be intuitive, following Material Design 3 guidelines.
*   **NFR-008:** Critical actions (like deleting an expense) should require confirmation.

## 4. Data Requirements
*   **User:** ID, Name, Email, Phone, AvatarURL, CreatedAt.
*   **Group:** ID, Name, CreatedBy, MembersList, CoverPhotoURL, CreatedAt.
*   **Expense:** ID, GroupID (optional), Description, Amount, Currency, PayerID, SplitDetails (Map of UserID -> Amount), ReceiptURL, CreatedAt, CreatedBy.
*   **Settlement:** ID, PayerID, PayeeID, Amount, Date, GroupID (optional).

## 5. Constraints & Assumptions
*   **Platform:** Android (min SDK 24).
*   **Backend:** Firebase (initially) for rapid development.
*   **Currency:** Initial support for USD and EUR.
*   **Language:** Initial support for English and Spanish.
