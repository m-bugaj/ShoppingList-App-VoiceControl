package pl.polsl.stt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    protected final static int RESULT_SPEECH = 1;
    private ImageButton btnSpeak;
    private TextView tvText;
    TextToSpeech tts;
    ArrayList<String> shoppingList;
    int itemFromTheList = 0;
    boolean isInList = false;
    boolean isAdding = false;
    boolean isQuantity = false;
    Integer number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.getDefault());
                }
            }
        });

        shoppingList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tvText);
        btnSpeak = findViewById(R.id.ibSpeak);


        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pl");
                try{
                    startActivityForResult(intent, RESULT_SPEECH);
                    tvText.setText("");
                } catch(ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Your device doesn't support Speech To Text", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case RESULT_SPEECH:
                if(resultCode==RESULT_OK && data!= null) {
                    String operation = "";
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvText.setText(text.get(0));
//                    tts.speak(text.get(0), TextToSpeech.QUEUE_ADD, null);

                    if(text.get(0).toLowerCase().contains("dodaj")) operation = "add";
                    if(text.get(0).toLowerCase().contains("usuń")) operation = "delete";
                    if(text.get(0).toLowerCase().contains("poprzedni")) operation = "back";
                    if(text.get(0).toLowerCase().contains("pomoc")) operation = "help";
                    if (text.get(0).toLowerCase().contains("lista zakupów") ||
                            text.get(0).toLowerCase().contains("listę zakupów") ||
                            text.get(0).toLowerCase().contains("lista")) {
                        operation = "list";
                    }
                    if(text.get(0).toLowerCase().contains("dalej")) operation = "next";
                    if(text.get(0).toLowerCase().contains("wyjdź")) operation = "exit";
                    if(isAdding && !isQuantity) operation = "quantity";
                    if(isAdding && isQuantity) operation = "unit";
//                    try {
//                        number = Integer.valueOf(text.get(0));
//                        operation = "quantity";
//                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid integer input");
//                    }

                    switch (operation){
                        case "add":
                            shoppingList.add(text.get(0).toLowerCase().replace("dodaj", "").replace(" ", ""));
                            isAdding = true;
                            tts.speak("Podaj teraz liczbę artykułów", TextToSpeech.QUEUE_ADD, null);
                            break;

                        case "delete":
                            String[] words = text.get(0).split(" ");
                            if (words.length >= 2) {
                                String secondWord = words[1];
                                shoppingList.removeIf(e -> e.contains(secondWord));
                                tts.speak("Usunięto " + secondWord + " z listy zakupów", TextToSpeech.QUEUE_ADD, null);
                            }
//                            shoppingList.removeIf(e -> e.contains(text.get(0).substring(1,2)));
//                            shoppingList.indexOf(shoppingList.)
//                            shoppingList.remove()
//                            shoppingList.add(text.get(0).toLowerCase().replace("dodaj", "").replace(" ", ""));
//                            isAdding = true;
                            break;

                        case "quantity":
                            shoppingList.set(shoppingList.size() - 1, shoppingList.get(shoppingList.size() - 1) + " " + text.get(0));
                            isQuantity = true;
                            tts.speak("Podaj teraz jednostkę", TextToSpeech.QUEUE_ADD, null);
                            break;

                        case "unit":
                            shoppingList.set(shoppingList.size() - 1, shoppingList.get(shoppingList.size() - 1) + " " + text.get(0));
                            isAdding = false;
                            isQuantity = false;
                            tts.speak("Dodaj kolejny artykuł", TextToSpeech.QUEUE_ADD, null);
                            break;

                        case "list":
                            if(!shoppingList.isEmpty()) {
                                tts.speak(shoppingList.get(itemFromTheList), TextToSpeech.QUEUE_ADD, null);
                                isInList = true;
                            }
                            break;

                        case "next":
                            if(itemFromTheList < shoppingList.size() - 1) {
                                if(isInList){
                                    itemFromTheList++;
                                    tts.speak(shoppingList.get(itemFromTheList), TextToSpeech.QUEUE_ADD, null);
                                }
                            } else {
                                tts.speak("Brak kolejnego elementu. Aby wyjść z listy powiedz wyjdź", TextToSpeech.QUEUE_ADD, null);
                            }
                            break;

                        case "back":
                            if(itemFromTheList > 0) {
                                if(isInList){
                                    itemFromTheList--;
                                    tts.speak(shoppingList.get(itemFromTheList), TextToSpeech.QUEUE_ADD, null);
                                }
                            } else {
                                tts.speak("To jest pierwszy element. Aby wyjść z listy powiedz wyjdź", TextToSpeech.QUEUE_ADD, null);
                            }
                            break;

                        case "exit":
                            if(isInList) isInList = false;
                            itemFromTheList = 0;
                            tts.speak("Dodaj kolejny artykuł", TextToSpeech.QUEUE_ADD, null);
                            break;

                        case "help":
                            tts.speak("Aby dodać artykuł kliknij w przycisk, powiedz Dodaj nazwa artykułu, następnie kliknij w przycisk podaj ilość produktu, następnie podaj jednostkę.", TextToSpeech.QUEUE_ADD, null);
                            tts.speak("Aby usunąć artykuł z listy, powiedz usuń nazwa artykułu.", TextToSpeech.QUEUE_ADD, null);
                            tts.speak("Aby odczytać listę zakupów, powiedz lista zakupów będąc w menu dodawania artykułów.", TextToSpeech.QUEUE_ADD, null);
                            tts.speak("Aby przejść do kolejnego elementu listy zakupów, powiedz dalej będąc w liście zakupów.", TextToSpeech.QUEUE_ADD, null);
                            tts.speak("Aby przejść do poprzedniego elementu listy zakupów, powiedz poprzedni będąc w liście zakupów.", TextToSpeech.QUEUE_ADD, null);
                            tts.speak("Aby wyjść z listy zakupów, powiedz wyjdź będąc w liście zakupów.", TextToSpeech.QUEUE_ADD, null);
                            break;
                    }
                }
                break;
        }
    }
}