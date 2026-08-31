# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 5/5 (100.0%)
- **Function parity:** 11/11 matched (target 14) — 100.0%
- **Class/type parity:** 10/10 matched (target 12) — 100.0%
- **Combined symbol parity:** 21/21 matched (target 26) — 100.0%
- **Average inline-code cosine:** 0.26 (function body across 2 matched files)
- **Average documentation cosine:** 0.00 (doc text across 2 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 5 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. ty_match

- **Target:** `sqlx.TyMatch [PROVENANCE-FALLBACK]`
- **Similarity:** 0.43
- **Dependents:** 0
- **Priority Score:** 1505.7
- **Functions:** 9/9 matched (target 10)
- **Missing functions:** _none_
- **Types:** 6/6 matched (target 7)
- **Missing types:** _none_
- **Tests:** 3/3 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `sqlx/src/ty_match.rs` vs expected `ty_match.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:sqlx/src/ty_match.rs` vs expected `ty_match.rs`
- **Proposed provenance header:** `// port-lint: source ty_match.rs` (current: `// port-lint: source sqlx/src/ty_match.rs`)
- **Proposed provenance header:** `// port-lint: tests ty_match.rs` (current: `// port-lint: tests sqlx/src/ty_match.rs`)
- **Lint issues:** 2

### 2. spec_error

- **Target:** `sqlx.SpecError [PROVENANCE-FALLBACK]`
- **Similarity:** 0.10
- **Dependents:** 0
- **Priority Score:** 609.0
- **Functions:** 2/2 matched (target 4)
- **Missing functions:** _none_
- **Types:** 4/4 matched (target 5)
- **Missing types:** _none_
- **Tests:** 1/1 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `sqlx/src/spec_error.rs` vs expected `spec_error.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:sqlx/src/spec_error.rs` vs expected `spec_error.rs`
- **Proposed provenance header:** `// port-lint: source spec_error.rs` (current: `// port-lint: source sqlx/src/spec_error.rs`)
- **Proposed provenance header:** `// port-lint: tests spec_error.rs` (current: `// port-lint: tests sqlx/src/spec_error.rs`)
- **Lint issues:** 2

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Matched

| Source | Target | Path |
|--------|--------|------|
| `any.mod` | `sqlx.Any` | `any/mod` |
| `lib` | `sqlx.Lib` | `lib` |
| `macros.mod` | `macros.Mod` | `macros/mod` |

