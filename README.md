# Crypto Tracker 🚀  
Crypto Tracker is a sleek Android application that allows users to monitor real-time prices of popular cryptocurrencies. Users can explore a detailed list of coins, track trends with an interactive chart, and dive into in-depth details about each currency.  

---

## Screenshots

- **Phone Screen:**  
  <p float="left">
    <img src="https://github.com/user-attachments/assets/ae4e47b2-9b03-4469-a72d-e57228f44707" width="200" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/fd49f029-5774-484c-95f1-d19fb086af1f" width="200" /> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/58c55c58-6e52-418f-99f7-bdac111a1714" width="200" /> 
    
  </p>  

- **Tablet Screen:**
  <p float="left">
    <img src="https://github.com/user-attachments/assets/274af1c8-bc84-43b2-a29d-6bd249732ee7" height="200" /> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/095a8e0e-1092-40f5-85e0-ada223c89200" height="200" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="https://github.com/user-attachments/assets/a61bbd85-fdff-4bde-bdc7-7ef7f481b69f" height="200" />
  </p>

---

## Features  
- **Comprehensive Coin List**  
  - Displays the coin image, name, symbol, current price, and 24-hour percentage change.  
  - Pull-to-refresh functionality for real-time updates.  

- **Detailed Coin View**  
  - Navigate to a coin’s detail screen with additional data such as market cap and historical price trends.  
  - Interactive custom chart showcasing price history and trends.  

- **Dynamic Theming**  
  - Supports Material Design dynamic theming, where the app's colors change to match your wallpaper color accents.  

- **Optimized for All Screens**  
  - Utilizes Material 3 adaptive navigation for a smooth experience across tablets and larger screens.  

---

## Tech Stack 🛠  
- **Architecture & Design**  
  - Clean Architecture.  
  - MVI (Model-View-Intent) Design Pattern.  

- **UI**  
  - Jetpack Compose for modern UI.  
  - Custom chart component for price trends.  
  - Material Design dynamic theming.  

- **Networking & Dependency Injection**  
  - [Ktor](https://ktor.io/) for efficient network calls.  
  - [Koin](https://insert-koin.io/) for dependency injection.  

- **Additional Features**  
  - Kotlin Coroutines and Flows for asynchronous operations.  
  - Compose State for dynamic UI updates.  
  - Material Pull-to-Refresh.  

---

## API  
This app uses the [CoinCap API](https://docs.coincap.io/) to fetch real-time cryptocurrency data, including prices, market caps, and historical trends.  

---

## Installation  
1. Clone this repository:  
   ```bash
   https://github.com/priyatoshd/Crypto-Tracker.git
2. Open the project in Android Studio.
3. Build and run the app on your preferred emulator or device.

---

## Contributions  
Contributions are welcome!  

Here’s how you can help:  
1. **Fork the repository**:  
   Click on the "Fork" button at the top-right corner of this page.  

2. **Create a branch**:  
   Create a new branch for your feature or bug fix:  
   ```bash
   git checkout -b feature-name
3. **Make changes and commit**:
   <br/>Add your changes and commit them:
   ```bash
   git add .
   git commit -m "Description of changes"
4. **Push your branch**:
   <br/>Push the changes to your branch:
   ```bash
   git push origin feature-name  
5. **Submit a pull request**:
   <br/>Go to the "Pull Requests" tab in the repository, and submit your pull request.
