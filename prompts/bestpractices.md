# Android Development Best Practices (Java Enterprise)

## 1. Code Style & Standards

### 1.1 Naming Conventions
*   **Classes:** PascalCase (e.g., `ExpenseRepository`).
*   **Methods/Variables:** camelCase (e.g., `calculateTotal`, `userList`).
*   **Constants:** UPPER_SNAKE_CASE (e.g., `MAX_RETRY_COUNT`).
*   **Layout Files:** snake_case with prefix (e.g., `activity_login.xml`, `fragment_group_list.xml`, `item_expense.xml`).
*   **Resource IDs:** snake_case (e.g., `tv_user_name`, `btn_submit`).

### 1.2 Java Best Practices
*   **Null Safety:** Always use `@Nullable` and `@NonNull` annotations to prevent NullPointerExceptions. Use `java.util.Optional` for return types where applicable.
*   **Immutability:** Prefer `final` fields and immutable objects. Use Builders for complex object creation.
*   **Logging:** Use Timber instead of standard `Log` class for better tag handling and release stripping.
*   **Hardcoding:** Never hardcode strings or dimensions. Use `strings.xml` and `dimens.xml`.

## 2. Architecture (Clean + MVVM)

### 2.1 Layer Separation
*   **Domain Layer Independence:** The Domain layer must NOT have any Android dependencies (no `android.*` imports). It should be pure Java.
*   **Unidirectional Data Flow:** Data should flow from Data -> Domain -> ViewModel -> UI. Events flow UI -> ViewModel -> Domain.
*   **State Management:** ViewModels should expose a single State object (or specific LiveData/Flows) that the UI observes. Avoid multiple mutable states in the Activity/Fragment.

### 2.2 Dependency Injection
*   **Constructor Injection:** Prefer constructor injection over field injection.
*   **Scopes:** Use `@Singleton` sparingly. Use `@ActivityScoped` or `@ViewModelScoped` to manage memory efficiently.

## 3. UI & UX

### 3.1 Layouts
*   **ConstraintLayout:** Use `ConstraintLayout` to create flat hierarchies and improve rendering performance.
*   **ViewBinding:** Use ViewBinding instead of `findViewById` for type safety and null safety.
*   **Themes:** Use Material Components themes and styles. Avoid hardcoding colors in layouts; use theme attributes (e.g., `?attr/colorPrimary`).

### 3.2 Threading
*   **Main Thread:** Never perform database or network operations on the Main Thread. Use RxJava Schedulers or Executors.
*   **Lifecycle:** Ensure background tasks are cancelled when the lifecycle owner is destroyed to prevent memory leaks (use `CompositeDisposable` in RxJava).

## 4. Performance Optimization

*   **RecyclerView:** Always use `DiffUtil` for efficient list updates. Avoid nested RecyclerViews if possible.
*   **Image Loading:** Use Glide/Picasso with proper caching strategies. Resize images before displaying.
*   **Memory Leaks:** Use LeakCanary in debug builds to detect leaks. Avoid passing `Context` to ViewModels or background threads (use `ApplicationContext` if absolutely necessary).

## 5. Security

*   **Data Storage:** Do not store sensitive data (tokens, passwords) in SharedPreferences. Use `EncryptedSharedPreferences`.
*   **Network:** Use HTTPS for all network calls. Implement Certificate Pinning for high security.
*   **Obfuscation:** Enable R8/ProGuard in release builds to shrink and obfuscate code.

## 6. Testing

*   **Unit Tests:** Aim for high coverage (>80%) in the Domain and Data layers. Mock dependencies using Mockito.
*   **UI Tests:** Write Espresso tests for critical user journeys (e.g., "User can log in", "User can add expense").
*   **Test Data:** Use factories or builders to create test data objects.

## 7. Version Control (Git)

*   **Commit Messages:** Follow conventional commits (e.g., `feat: add login screen`, `fix: crash on rotation`).
*   **Branches:** Use feature branches (`feature/login`) and pull requests. Never push directly to `main` or `master`.
*   **Code Review:** All code must be reviewed by at least one peer before merging.
