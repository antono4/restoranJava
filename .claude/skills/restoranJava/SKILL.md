```markdown
# restoranJava Development Patterns

> Auto-generated skill from repository analysis

## Overview
This skill covers the core development patterns and conventions used in the `restoranJava` repository, a JavaScript project with no detected framework. It documents file naming, code style, commit patterns, and testing approaches, enabling contributors to maintain consistency and efficiency.

## Coding Conventions

### File Naming
- Use **camelCase** for all file names.
  - Example: `orderManager.js`, `menuList.js`

### Imports
- Use **relative import paths** for modules.
  ```javascript
  import { getMenu } from './menuList';
  ```

### Exports
- Use **named exports** for functions, objects, or constants.
  ```javascript
  // In menuList.js
  export function getMenu() { ... }
  export const MENU_TYPES = ['breakfast', 'lunch', 'dinner'];
  ```

### Commit Patterns
- Commit messages are **freeform** (no strict prefixes).
- Average commit message length: ~45 characters.
  - Example: `Add new dish to lunch menu`
  - Example: `Fix bug in order calculation`

## Workflows

### Adding a New Feature
**Trigger:** When implementing a new functionality  
**Command:** `/add-feature`

1. Create a new file using camelCase naming.
2. Implement the feature using named exports.
3. Import any required modules using relative paths.
4. Write or update corresponding test files (`*.test.js`).
5. Commit changes with a clear, descriptive message.

### Fixing a Bug
**Trigger:** When resolving a reported issue or bug  
**Command:** `/fix-bug`

1. Locate the affected file(s).
2. Apply the necessary fix, following code style conventions.
3. Update or add tests to cover the bug fix.
4. Commit with a message describing the fix.

### Writing Tests
**Trigger:** When adding or updating tests  
**Command:** `/write-test`

1. Create or update test files using the `*.test.js` pattern.
2. Write tests for all new or modified functions.
3. Run tests using the project's test runner (framework unknown).
4. Commit test changes with a descriptive message.

## Testing Patterns

- Test files are named with the `*.test.js` pattern.
  - Example: `orderManager.test.js`
- The testing framework is **unknown**, but standard JavaScript test conventions apply.
- Place tests alongside or near the modules they cover.
- Ensure all exported functions have corresponding tests.

## Commands
| Command       | Purpose                                 |
|---------------|-----------------------------------------|
| /add-feature  | Guide for adding a new feature          |
| /fix-bug      | Steps for fixing a bug                  |
| /write-test   | Instructions for writing or updating tests |
```
