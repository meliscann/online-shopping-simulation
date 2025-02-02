# online-shopping-simulation

This project simulates a simple online shopping system using Java and design patterns. 
The following design patterns are implemented:

- **Singleton**: Manages a shopping cart with a list of products.
- **Decorator**: Applies discounts to products.
- **Factory**: Creates payment methods (Credit Card, Debit Card, Cash on Delivery).
- **Observer**: Notifies customers about the order status through different channels (Email and SMS).

## 📌 Technologies Used:
- Java
- Object-Oriented Design Patterns (Singleton, Decorator, Factory, Observer)

## 🛒 Features:

### 1. **Singleton Design Pattern: Shopping Cart**
   - Manages a list of products.
   - Ensures only one instance of the shopping cart exists.
   - Methods for adding, removing, and displaying products.

### 2. **Decorator Design Pattern: Discount on Products**
   - Allows applying discounts to products.
   - Shows discounted prices.

### 3. **Factory Design Pattern: Payment Methods**
   - Offers different payment methods:
     - **Credit Card**
     - **Debit Card**
     - **Cash on Delivery**
   - The `PaymentMethodFactory` creates the corresponding payment method.

### 4. **Observer Design Pattern: Order Status Notifications**
   - Notifies customers via Email or SMS about the order status updates.
   - Order status changes: **PLACED**, **DISPATCHED**, **DELIVERED**.

## 🛠 Installation & Usage:

