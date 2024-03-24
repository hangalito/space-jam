## Space Jam: Explore NASA's Picture of the Day

**Space Jam** is an Android application that lets you explore the wonders of space through NASA's Astronomy Picture of the Day (APOD) API. 

**Features:**

- View the current "Picture of the Day" provided by NASA.
- Optionally, explore pictures from previous days (requires implementing date selection functionality).

**Getting Started:**

1. **Prerequisites:**
    - Android Studio
    - A NASA APOD API Key (refer to [https://api.nasa.gov/](https://api.nasa.gov/))

2. **Setup:**
    - Clone this repository.
    - Create a file named `gradle.properties` in the project's root directory (if it doesn't exist).
    - Inside `gradle.properties`, add a line: `NASA_API_KEY=YOUR_ACTUAL_API_KEY` (replace with your actual key).
    - **Important:** Exclude `gradle.properties` from version control (e.g., using a `.gitignore` file).

3. **Run the app:**
    - Open the project in Android Studio.
    - Ensure your Gradle sync is successful.
    - Run the app on an emulator or connected device.

**Technology Stack:**

- Kotlin (programming language)
- Retrofit (networking library)
- kotlinx.serialization (data serialization)

**Contributing:**

We welcome contributions to this project! Feel free to fork the repository and submit pull requests with improvements or new features. 

**License:**

This project is licensed under the Apache License 2.0 (see LICENSE file for details).
