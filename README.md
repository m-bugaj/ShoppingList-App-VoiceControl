# VoiceShop - Shopping List 🛒🎤

## Application Title and Purpose:
- **Title:** VoiceShop (Polish Language Support)
- **Purpose:** The purpose of the VoiceShop application is to facilitate users in creating a shopping list by allowing them to dictate products using their voice. The application converts speech to text, creating a readable shopping list, which enables users to conveniently plan their purchases without the need for typing.

## Basic Functional Requirements:
1. **Speech-to-Text Conversion:** The application should allow users to press a button and start processing speech into text.
2. **Creating a Shopping List:** After converting speech to text, the application should add products to the shopping list.
3. **Removing Products:** Users should be able to remove products from the shopping list.
4. **Product Search:** The application should allow searching for products in a database or based on user-provided information.
5. **Reading the Shopping List:** The application reads one product at a time from the list and allows the user to navigate to the next or previous product.
6. **Voice Command Handling:** The application responds to voice commands from the user, such as adding, removing, or reading the shopping list.

## Basic Voice Commands (Polish Language):
1. **"Dodaj"**
   - Action: Adds a product to the shopping list.
2. **"(liczba całkowita)"**
   - After issuing the command to add a product to the shopping list, the application will ask for the quantity. User should speak a positive integer.
3. **"(jednostka) np. kartony, opakowania, sztuki, kilogramy, lub gramy"**
   - After issuing the command for the quantity of products, the application will ask for the unit. User should speak the unit.
4. **"Usuń"**
   - Action: Removes a product from the shopping list.
5. **"Lista zakupów"**
   - Action: Reads the shopping list aloud.
6. **"Dalej"**
   - Action: Moves to the next product on the list and reads it.
7. **"Wyjdź"**
   - Action: Exits the shopping list.
8. **"Pomoc"**
   - Action: Reads the application user guide.

## Expanded Voice Commands (Not Implemented):
1. **"Zapisz listę zakupów"**
   - Action: Saves the current shopping list on the device.
2. **"Czy mam na liście jajka?"**
   - Action: Checks if the product "jajka" is on the shopping list.
3. **"Dodaj 3 sztuki mąki do listy"**
   - Action: Adds three pieces of the product "mąka" to the shopping list.
4. **"Wyczyść listę"**
   - Action: Removes all products from the shopping list.
5. **"Zaktualizuj ilość jabłek na liście na 5"**
   - Action: Updates the quantity of the product "jabłka" on the shopping list to 5 pieces.
6. **"Dodaj kategorię do listy zakupów"**
   - Action: Adds a category of products to the shopping list, e.g., "produkty świąteczne".

## Example Use Case Scenario:
A user starts using the VoiceShop application while preparing for grocery shopping:
1. The user presses the "Process Speech" button.
2. Says "Dodaj mleko".
3. The application prompts the user to specify the quantity verbally. The user responds with, for example, "dwa".
4. The application then asks the user to specify the unit verbally. The user responds with, for example, "kartony".
5. The application adds "mleko" with the specified quantity and unit to the shopping list.
6. The user can now say "Usuń mleko" to remove it from the list.
7. To review the current shopping list, the user can say "Lista zakupów".
8. If the user wants to move to the next item on the list, they can say "Dalej".
9. Similarly, if the user wishes to go back to the previous item, they can say "Poprzedni".
10. After finishing shopping, the user can exit the shopping list by saying "Wyjdź".

## Method of Calculating the Command Set Quality Coefficient:
This method assesses the command set based on three key factors:
- **Number of Commands:** The more diverse commands, the better.
- **Command Length:** Shorter commands are easier to pronounce and understand.
- **Phonetic Similarity:** More phonetically diverse commands are less likely to be misunderstood.

Based on these factors, the quality of the command set is evaluated. The more short and diverse commands with varying pronunciation, the better the command set. This method helps ensure that the command set is user-friendly and less prone to errors.
