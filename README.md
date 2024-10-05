## Introduction

This project is focused on integrating the Pepper robot with Android Studio to create an adaptable development environment for robot application programming. The Pepper robot, developed by SoftBank Robotics, is designed for human interaction and is capable of recognizing faces and basic human emotions. This README outlines the procedures for setting up the Pepper SDK and configuring Android Studio on a Linux system, specifically emphasizing compatibility with Ubuntu 20 due to emulator requirements.

## Prerequisites

- **Software Requirements:**
  - Android Studio (specific version: January 17th, 2023 Canary 1)
  - Java Development Kit (JDK)
- **Hardware Requirements:**
  - A compatible computer with Linux Operating System (Ubuntu 20 recommended)
  - Pepper Robot for testing and deployment

## Installation

1. **Installing JDK and Android Studio:**
   - Open a terminal and execute the following commands:
     ```
     sudo apt install openjdk-11-jdk
     sudo add-apt-repository ppa:maarten-fonville/android-studio
     sudo apt update
     sudo apt install android-studio
     ```
   - Address to download the specific version of Android Studio: developer.android.com/studio/archive

2. **Setting up Pepper SDK:**
   - After installing Android Studio, navigate to `File -> Settings -> Plugins`.
   - Search for "Pepper SDK" and install. If the SDK is not available in the Plugin store:
     - Download the SDK manually from the address: plugins.jetbrains.com/plugin/8354-pepper-sdk
     - Install the SDK from the disk.

## Configuration

- **Creating a New Project:**
  - Start Android Studio and create a new project with an Empty Activity.
  - Set up the project to use Android 6.0 (Marshmallow) and Java as the programming language.

## Robot Application Development

- **Integrating Pepper SDK:**
  - Ensure the Pepper SDK is properly integrated into your project.
  - Include dependencies in your `build.gradle`:
    ```groovy
    implementation 'com.aldebaran:qisdk:1.7.5'
    implementation 'com.aldebaran:qisdk-design:1.7.5'
    ```
  - Adjust `settings.gradle` to include:
    ```groovy
    maven {
       url 'https://qisdk.softbankrobotics.com/sdk/maven/'
    }
    ```

## Emulator Setup

- Detailed steps to configure and use the emulator for testing the applications on Pepper without a physical robot.

## Troubleshooting

- Solutions to common setup and development issues encountered while working with Pepper and Android Studio.

## References

- Official documentation and additional resources for further learning about developing applications for Pepper.

## Project Structure Overview

### `activity_main.xml`

This XML file sets up the layout for the main activity of our Android application, utilizing a `ConstraintLayout` for flexible positioning and dynamic resizing of UI components. Here are the details:

- **TextViews:**
  - `textView1`: Displays the headline "I'm Luna" in a large, pink font, serving as the main title.
  - `textView2`: Shows the text "CAED" beneath the main title, possibly indicating a category or description.
- **ImageView:**
  - `imageView2`: Positioned below the TextViews, displaying an image resource named `kentstate`, likely representing a logo or related graphic.
- **Constraints:** The layout uses constraints to center and vertically chain the TextViews and ImageView, ensuring a balanced layout on various screen sizes.

### `AndroidManifest.xml`

This XML file configures essential settings for the application, including permissions, application and activity declarations:

- **Permissions:**
  - `INTERNET`: Allows the application to make network requests.
  - `ACCESS_NETWORK_STATE`: Enables the app to access information about the network state.
  - `READ_EXTERNAL_STORAGE` and `WRITE_EXTERNAL_STORAGE`: Permits the app to read from and write to the device's external storage.
- **Application and Activity Configurations:**
  - The `MainActivity` is defined as the launcher activity, which is the first screen presented to the user upon opening the app.
  - Specifies the app theme and icons, which align with the project's visual identity.
- **Functionality:** The inclusion of specific permissions and features like `com.softbank.hardware.pepper` suggests the application is designed to interact with Pepper robot hardware, highlighting the focus on robotic integration.

## Usage and Functionality

Each component and configuration in these files plays a crucial role in the application's functionality:
- The UI elements in `activity_main.xml` contribute to a user-friendly interface, making interaction intuitive and engaging.
- The permissions and activity settings in `AndroidManifest.xml` ensure that the application operates smoothly, with proper access to network and storage resources, essential for tasks involving external APIs and data handling.


