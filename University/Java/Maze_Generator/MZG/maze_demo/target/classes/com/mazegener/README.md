# Maze Generator

This project is a simple maze generator application built using Java and JavaFX. It allows users to create mazes, adjust their dimensions, and visualize the longest paths within the maze. The application provides an interactive graphical interface for generating and exploring mazes.

## Features

- **Maze Generation**: Automatically generates a random maze using a recursive carving algorithm.
- **Adjustable Dimensions**: Users can change the maze's dimensions using a slider-based dialog.
- **Longest Path Visualization**:
  - Visualize the longest path from a specific starting point.
  - Find and display the overall longest path in the maze.
- **Interactive UI**: Built with JavaFX, featuring buttons and menus for user interaction.

## Folder Structure

The workspace contains the following structure:

```
maze-generator/
│
├── src/                     # Source files
│   ├── main/                # Main application package
│   └── resources/           # Resource files (e.g., FXML, images)
│
├── lib/                     # External libraries
│
├── doc/                     # Documentation files
│
└── README.md                # Project documentation file
```

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven (for building the project)
- JavaFX SDK (for running the JavaFX application)

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/your_username/maze-generator.git
   ```
2. Navigate to the project directory
   ```sh
   cd maze-generator
   ```
3. Build the project using Maven
   ```sh
   mvn clean install
   ```
4. Run the application
   ```sh
   mvn javafx:run
   ```

## Usage

- Launch the application using the provided executable JAR file or through your IDE.
- Use the menu options to create a new maze, adjust dimensions, and visualize paths.
- Explore the maze and interact with the UI elements to understand the maze structure and pathfinding.

## Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Acknowledgements

- [JavaFX](https://openjfx.io/) - The JavaFX library for building the user interface.
- [Maven](https://maven.apache.org/) - Dependency management and build automation tool.
- [GitHub](https://github.com/) - For version control and collaboration.
