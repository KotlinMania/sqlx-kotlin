# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 5/53 (9.4%)
- **Function parity:** 12/433 matched (target 130) — 2.8%
- **Class/type parity:** 10/105 matched (target 83) — 9.5%
- **Combined symbol parity:** 22/538 matched (target 213) — 4.1%
- **Average inline-code cosine:** 0.26 (function body across 2 matched files)
- **Average documentation cosine:** 0.00 (doc text across 2 matched files)
- **Cheat-zeroed Files:** 3
- **Critical Issues:** 5 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. sqlx.ty_match

- **Target:** `sqlx.TyMatch`
- **Similarity:** 0.43
- **Dependents:** 0
- **Priority Score:** 1505.7
- **Functions:** 9/9 matched (target 10)
- **Missing functions:** _none_
- **Types:** 6/6 matched (target 7)
- **Missing types:** _none_
- **Tests:** 3/3 matched

### 2. sqlx.spec_error

- **Target:** `sqlx.SpecError`
- **Similarity:** 0.10
- **Dependents:** 0
- **Priority Score:** 609.0
- **Functions:** 2/2 matched (target 4)
- **Missing functions:** _none_
- **Types:** 4/4 matched (target 5)
- **Missing types:** _none_
- **Tests:** 1/1 matched

### 3. any.mod

- **Target:** `sqlx.Any [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 110.0
- **Functions:** 1/1 matched (target 34)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 15)
- **Missing types:** _none_

### 4. sqlx.lib

- **Target:** `sqlx.Lib [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 79)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 56)
- **Missing types:** _none_

### 5. macros.mod

- **Target:** `macros.Mod [STUB]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 3)
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

