# Portfolio

A showcase of my work across **Software Development** and **Quality Assurance** — a single place that collects coding projects and testing knowledge as I build and document them.

## Quality Assurance

Hands-on automation reference and examples, focused on Selenium with Java:

- **Locator strategies** — finding single and multiple elements, relative locators
- **Browser interactions** — alerts, modals, iframes, tabs, cookies, navigation, screenshots
- **Actions API** — hover, double-click, context-click, drag-and-drop, scroll, copy/paste
- **Wait strategies** — implicit, explicit, and fluent waits

## Software Development

A growing set of projects grouped by language:

### Java
- **Maze Generator** — procedural maze generation
- **Data Structures** — implementations and exercises
- **Bank App** — a simple banking domain model
- **Taxi Dispatch Simulation** — event/dispatch simulation

### JavaScript
- **Local Air Hockey** — a local multiplayer air hockey game with a Node server (`Server.js`)

### Python
- **Parking Lot** — a parking-lot management exercise

## Repository Structure

```
Portfolio/
├─ Quality Assurance/
│  └─ Selenium Knowledge/java/   # Locators, interactions, actions, waits
└─ Software Development/
   ├─ Java/                      # Maze Generator, Data Structures, Bank App, Taxi Dispatch
   ├─ JavaScript/                # Local Air Hockey
   └─ Python/                    # Parking Lot
```

## Continuous Quality

A [SonarQube workflow](.github/workflows/sonarqube.yml) runs static analysis on the codebase via GitHub Actions.

## License

See [LICENSE](LICENSE).
