# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 5/53 (9.4%)
- **Function parity:** 12/433 matched (target 129) — 2.8%
- **Class/type parity:** 9/105 matched (target 81) — 8.6%
- **Combined symbol parity:** 21/538 matched (target 210) — 3.9%
- **Average inline-code cosine:** 0.17 (function body across 3 matched files)
- **Average documentation cosine:** 0.00 (doc text across 3 matched files)
- **Cheat-zeroed Files:** 3
- **Critical Issues:** 5 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. spec_error

- **Target:** `sqlx.SpecError [PROVENANCE-FALLBACK]`
- **Similarity:** 0.10
- **Dependents:** 0
- **Priority Score:** 10609.0
- **Functions:** 2/2 matched (target 4)
- **Missing functions:** _none_
- **Types:** 3/4 matched
- **Missing types:** `SpecError`
- **Tests:** 1/1 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `spec_error.rs` vs expected `spec_error.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:spec_error.rs` vs expected `spec_error.rs`
- **Proposed provenance header:** `// port-lint: source spec_error.rs` (current: `// port-lint: source spec_error.rs`)
- **Proposed provenance header:** `// port-lint: tests spec_error.rs` (current: `// port-lint: tests spec_error.rs`)
- **Lint issues:** 2

### 2. ty_match

- **Target:** `sqlx.TyMatch [PROVENANCE-FALLBACK]`
- **Similarity:** 0.43
- **Dependents:** 0
- **Priority Score:** 1505.7
- **Functions:** 9/9 matched (target 10)
- **Missing functions:** _none_
- **Types:** 6/6 matched (target 7)
- **Missing types:** _none_
- **Tests:** 3/3 matched
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `ty_match.rs` vs expected `ty_match.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:ty_match.rs` vs expected `ty_match.rs`
- **Proposed provenance header:** `// port-lint: source ty_match.rs` (current: `// port-lint: source ty_match.rs`)
- **Proposed provenance header:** `// port-lint: tests ty_match.rs` (current: `// port-lint: tests ty_match.rs`)
- **Lint issues:** 2

### 3. any.mod

- **Target:** `sqlx.Any [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 110.0
- **Functions:** 1/1 matched (target 34)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 15)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `any/mod.rs` vs expected `any/mod.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `any/mod.rs` vs expected `any/mod.rs`
- **Proposed provenance header:** `// port-lint: source any/mod.rs` (current: `// port-lint: source any/mod.rs`)
- **Proposed provenance header:** `// port-lint: source any/mod.rs` (current: `// port-lint: source any/mod.rs`)
- **Lint issues:** 2

### 4. lib

- **Target:** `sqlx.Lib [ZERO] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 78)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 55)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:lib.rs` vs expected `lib.rs`
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: tests lib.rs` (current: `// port-lint: tests lib.rs`)
- **Lint issues:** 12

### 5. macros.mod

- **Target:** `macros.Mod [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 3)
- **Missing functions:** _none_
- **Types:** 0/0 matched
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `macros/mod.rs` vs expected `macros/mod.rs`
- **Proposed provenance header:** `// port-lint: source macros/mod.rs` (current: `// port-lint: source macros/mod.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

