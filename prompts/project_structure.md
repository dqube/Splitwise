# Enterprise Project Structure (Java Android)

## 1. Overview
This project follows **Clean Architecture** principles combined with **MVVM (Model-View-ViewModel)**. It is designed to be scalable, testable, and maintainable. For an enterprise-level application, we recommend a **Multi-Module Architecture** to separate concerns and improve build times.

## 2. Module Structure
The project is divided into the following Gradle modules:

*   **:app** - The main application module. Connects features and handles dependency injection setup.
*   **:core** - Contains common utilities, extensions, base classes, and resources shared across modules.
*   **:domain** - Pure Java module. Contains Use Cases, Repository Interfaces, and Domain Models. **No Android dependencies.**
*   **:data** - Handles data retrieval (API, Database). Implements Repository interfaces from the Domain layer.
*   **:features** - A directory containing feature-specific modules (optional but recommended for large teams).
    *   **:features:auth** - Login, Signup, Profile.
    *   **:features:groups** - Group creation, listing, management.
    *   **:features:expenses** - Add expense, expense details.
    *   **:features:settlement** - Balances, settlement flows.

## 3. Detailed Package Structure

### A. Domain Layer (`:domain`)
This layer is the "Brain" of the app. It depends on nothing.
```
com.example.fairshare.domain
├── model/                  # Plain Java Objects (POJOs)
│   ├── User.java
│   ├── Group.java
│   └── Expense.java
├── repository/             # Interfaces defining data operations
│   ├── UserRepository.java
│   ├── GroupRepository.java
│   └── ExpenseRepository.java
├── usecase/                # Business logic (Interactors)
│   ├── auth/
│   │   ├── LoginUseCase.java
│   │   └── SignUpUseCase.java
│   ├── groups/
│   │   ├── CreateGroupUseCase.java
│   │   └── GetUserGroupsUseCase.java
│   └── expenses/
│       └── AddExpenseUseCase.java
└── common/                 # Domain specific errors/results
    └── Result.java
```

### B. Data Layer (`:data`)
This layer implements the Domain interfaces. It depends on `:domain`.
```
com.example.fairshare.data
├── di/                     # Hilt Modules for Data layer
│   ├── NetworkModule.java
│   └── DatabaseModule.java
├── local/                  # Room Database
│   ├── AppDatabase.java
│   ├── dao/
│   │   ├── UserDao.java
│   │   └── ExpenseDao.java
│   └── entity/             # Database specific models (mapped to Domain models)
│       ├── UserEntity.java
│       └── ExpenseEntity.java
├── remote/                 # Network API
│   ├── ApiService.java
│   └── dto/                # Data Transfer Objects (API responses)
│       ├── UserDto.java
│       └── GroupDto.java
├── repository/             # Implementation of Domain repositories
│   ├── UserRepositoryImpl.java
│   └── GroupRepositoryImpl.java
└── mapper/                 # Mappers between Entity/DTO and Domain Models
    └── UserMapper.java
```

### C. Presentation Layer (`:features:xxx` or inside `:app`)
This layer handles UI and User Input. It depends on `:domain`.
```
com.example.fairshare.features.auth
├── LoginActivity.java      # or Fragment
├── LoginViewModel.java
├── LoginState.java         # POJO for UI State
└── di/                     # Feature specific DI
    └── AuthModule.java

com.example.fairshare.features.groups
├── GroupListFragment.java
├── GroupListViewModel.java
└── GroupAdapter.java       # RecyclerView Adapter
```

### D. Core Module (`:core`)
Common infrastructure.
```
com.example.fairshare.core
├── base/
│   ├── BaseActivity.java
│   ├── BaseFragment.java
│   └── BaseViewModel.java
├── utils/
│   ├── DateUtils.java
│   ├── ValidationUtils.java
│   └── Constants.java
└── widget/                 # Custom Views
    └── LoadingView.java
```

## 4. Key Files & Configuration

*   **build.gradle (Project):** Define common versions (ext variables) for libraries to ensure consistency across modules.
*   **build.gradle (Module):** Each module declares its specific dependencies.
    *   `:domain` has NO android dependencies (only RxJava/JUnit).
    *   `:data` has Retrofit, Room, etc.
    *   `:app` / `:features` have AndroidX, Material Components, Glide, Hilt.
*   **AndroidManifest.xml:**
    *   `:app` manifest contains the `<application>` tag and main entry point.
    *   Feature manifests generally only need to declare their specific activities (if not using single-activity architecture).

## 5. Dependency Flow
`App/Feature` -> `Domain` <- `Data`
*   **UI** calls **ViewModel**.
*   **ViewModel** executes **UseCase**.
*   **UseCase** calls **Repository Interface**.
*   **Repository Implementation** (in Data) fetches data from **Local** or **Remote** sources.

## 6. Testing Strategy
*   **Unit Tests (JUnit 4/5 + Mockito):**
    *   Test all **UseCases** in `:domain`.
    *   Test **ViewModels** in `:features` (mocking UseCases).
    *   Test **RepositoryImpl** logic (mocking DataSources).
*   **Integration Tests:**
    *   Test Room DAOs (AndroidJUnit4).
*   **UI Tests (Espresso):**
    *   Test critical user flows (Login, Add Expense).
