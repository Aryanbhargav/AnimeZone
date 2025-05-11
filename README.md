# AnimeZone

## App Previews
<img width="327" alt="Screenshot_20250511_190616" src="https://github.com/user-attachments/assets/efa9a39e-b989-4a83-b88b-97b34b8dd52d" />
<img width="327" alt="Screenshot_20250511_190639" src="https://github.com/user-attachments/assets/d8d6bdd3-13e0-4c9f-baf8-62c537b2fbb8" />

## Setup Process

1. **Download the ZIP**  
   - Go to the [GitHub repository](<https://github.com/Aryanbhargav/AnimeZone/tree/main>).  
   - Click on the **"Code"** button and select **"Download ZIP"**.  
   - Extract the downloaded ZIP file to your desired location.  

2. **Import into Android Studio**  
   - Open **Android Studio**.  
   - Click on **"Open"**.  
   - Navigate to the extracted folder and select it.  
   - Wait for the project to sync and build.  

3. **Run the App**  
   - Connect an emulator or physical device.
   - Run the project from 'main' branch
   - Click on the **Run** button ▶️ to launch the app.  

## Project Architecture

AnimeZone follows **Clean Architecture** with the **MVVM** design pattern. The project is structured into three main layers:  

1. **Data Layer**  
   - Handles data operations from **Network (Retrofit)** and **Local Database (Room)**.  

2. **Domain Layer**  
   - Contains repository implementations to abstract data sources.  

3. **UI Layer**  
   - Built with **Jetpack Compose** and consists of **Composables** and **ViewModel** to manage UI state.  

### Tech Stack

- **Networking** → Retrofit  
- **Dependency Injection** → Hilt  
- **Async Operations** → Kotlin Coroutines & Flow  
- **UI Framework** → Jetpack Compose  
- **Image Loading** → Coil  
- **Lifecycle Management** → ViewModel 

