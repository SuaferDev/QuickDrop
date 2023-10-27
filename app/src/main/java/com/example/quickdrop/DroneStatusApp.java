package com.example.quickdrop;
/*
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class DroneStatusApp {

    public static void main(String[] args) {
        try {
            // Инициализация Firebase
            FileInputStream serviceAccount = new FileInputStream("path/to/your/firebase/private/key/file.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://your-firebase-project.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

            // Получение ссылки на базу данных
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("drones/drone1");

            // Лэтч для ожидания ответа из базы данных
            CountDownLatch latch = new CountDownLatch(1);

            // Получение данных о дроне
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Drone drone = dataSnapshot.getValue(Drone.class);
                        System.out.println("Дрон: " + drone);
                    } else {
                        System.out.println("Данные о дроне не найдены");
                    }
                    latch.countDown();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("Ошибка при получении данных: " + databaseError.getMessage());
                    latch.countDown();
                }
            });

            // Ожидание получения данных из базы данных
            latch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Drone {
        public boolean isDelivering;
        public boolean isAtHomePoint;
        public boolean isWorking;
        public String errorCode;

        @Override
        public String toString() {
            return "Drone{" +
                    "isDelivering=" + isDelivering +
                    ", isAtHomePoint=" + isAtHomePoint +
                    ", isWorking=" + isWorking +
                    ", errorCode='" + errorCode + '\'' +
                    '}';
        }
    }
}
*/