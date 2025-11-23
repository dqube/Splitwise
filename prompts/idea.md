# Project Idea: Splitwise Clone (Android App)

## 1. App Overview
**Name Suggestion:** FairShare / SplitMate / DivvyUp
**Concept:** An Android application designed to help friends, roommates, and groups track shared expenses and settle debts easily. The goal is to reduce the awkwardness of money and ensure everyone pays their fair share.

## 2. Core Features

### A. User Management
*   **Authentication:** Sign up/Login via Email, Phone Number, or Google Account.
*   **Profile:** User avatar, name, and contact info.
*   **Friend List:** Add friends by email or phone number to start tracking expenses individually.

### B. Group Management
*   **Create Groups:** Create groups for specific occasions (e.g., "Trip to Vegas", "Apartment 4B", "Lunch Buddies").
*   **Invite Members:** Invite friends to groups via link or contact list.
*   **Group Settings:** Edit group name, cover photo, and simplify debts option.

### C. Expense Tracking
*   **Add Expense:**
    *   Enter amount and description.
    *   Select payer (single person or multiple people).
    *   Select who splits the bill (all group members or specific subset).
*   **Split Options:**
    *   **Equally:** Divide by number of people.
    *   **Unequally:** Specify exact amounts for each person.
    *   **Percentages:** Split by percentage (total must equal 100%).
    *   **Shares:** Split by "shares" (e.g., User A has 2 shares, User B has 1).
*   **Receipts:** Attach photo of receipt to expense.
*   **Categories:** Tag expenses (Food, Transport, Rent, Movies).

### D. Balances & Settlement
*   **Dashboard:** View total "You owe" and "You are owed" at a glance.
*   **Group Balances:** Visual representation of who owes whom within a group.
*   **Settle Up:** Record a cash payment or integrate with payment links (UPI, PayPal, Venmo deep links) to settle debts.
*   **Simplify Debts:** Algorithm to minimize the number of transactions needed to settle up the group (e.g., A owes B $10, B owes C $10 -> A pays C $10).

### E. Activity & Notifications
*   **Activity Log:** History of all added expenses, edits, and settlements.
*   **Push Notifications:** Notify users when they are added to an expense, a group, or when a settlement is recorded.

### F. Localization & Settings
*   **Multi-language Support:** Support for English and Spanish.
*   **Multi-currency Support:** Support for US Dollars ($) and Euros (€). Users can select their preferred currency for groups or individual expenses.

## 3. Technical Architecture (Android)

### A. Tech Stack
*   **Language:** Java
*   **UI Framework:** XML Layouts (Android View System)
*   **Architecture Pattern:** MVVM (Model-View-ViewModel) with Clean Architecture principles.
*   **Dependency Injection:** Hilt (or Dagger 2)
*   **Asynchronous Programming:** RxJava 3 or Java Concurrency (Executors)

### B. Data & Networking
*   **Local Database:** Room (SQLite) for offline caching and smooth UX.
*   **Backend (MVP):** Firebase (Firestore for NoSQL DB, Authentication, Cloud Functions).
*   **Backend (Scalable):** Spring Boot (Java) or Node.js with PostgreSQL.
*   **API Communication:** Retrofit (if using custom backend).

### C. key Libraries
*   **Navigation:** Jetpack Navigation Component.
*   **Image Loading:** Glide or Picasso.
*   **Serialization:** Gson or Jackson.

## 4. User Flow
1.  **Onboarding:** User signs up -> Grants contact permissions (optional).
2.  **Home Screen:** Shows total balance summary and list of friends/groups with outstanding balances.
3.  **Action:** User clicks "+" FAB (Floating Action Button) to add an expense.
4.  **Add Expense Flow:** Select Group/Friend -> Enter Amount -> Description -> Choose Split Method -> Save.
5.  **Result:** Balances update immediately. Notifications sent to involved users.

## 5. Future Enhancements
*   **OCR Receipt Scanning:** Automatically extract amount and items from a receipt photo.
*   **Recurring Expenses:** Auto-add rent or subscription bills monthly.
*   **Export Reports:** Export group expenses to PDF or CSV.
