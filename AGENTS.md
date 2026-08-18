# Repository Guidelines

## Project Structure & Module Organization
- Root-level files (`CsesIntroductory.java`, `SortingandSearching.java`, `DynamicProgramming.java`, `GraphAlgorithms.java`) are consolidated Java files: each bundles many CSES problem solutions as static methods inside one public class (named after the file), sharing a `FastScanner` inner class for fast stdin I/O and small POJO helper classes (`Pair`, `Edge`, `State`, `Cell`, `Node`, etc.) declared above the public class.
- `main()` in each of those files dispatches to a single problem at a time by commenting/uncommenting the relevant method call — only one call should be left active when running.
- `CSES Problems/` holds one-file-per-problem solutions organized by CSES category: `Introductory Problems/`, `Sorting and Searching/`, `Graph Algorithms/`, `Dynamic Programming/` (currently empty). Loose files directly under `CSES Problems/` predate this reorg (see git history) and may duplicate a categorized version.
- `ai/` contains study notes (`plan.md`, `research.md`, `Revision.md`), not code.
- A handful of problems also have a `.cpp` solution alongside the `.java` one (e.g. `Apartments`, `ConcertsTicket`).

## Build, Test, and Development Commands
No build tool (no Maven/Gradle/npm) — compile and run files directly with the JDK:
```
javac GraphAlgorithms.java && java GraphAlgorithms
```
Same pattern for files under `CSES Problems/<category>/`. `.class` output is gitignored, so it's fine to compile in place.

## Coding Style & Naming Conventions
- No linter/formatter config present — match existing style: 4-space indentation, one public class per file named after the file, small `PascalCase` helper classes for tuples/state declared at the top of the file above the public class.
- Problem-solving methods are named after the CSES problem (e.g. `RoadConstruction`, `ShortestRoutesII`) and use PascalCase rather than Java's usual camelCase for methods.
- Fast I/O goes through the shared `FastScanner` inner class rather than `Scanner`/`BufferedReader` directly.

## Commit & Pull Request Guidelines
`git log` shows short, descriptive commits summarizing what was added, mixing imperative and gerund forms (e.g. "Add graph algorithms, sorting & searching, and DP solutions", "Reorganize files into CSES Problems folder, add CsesIntroductory.java"). No PR template exists; this is a personal practice repo with direct commits to `master`.
