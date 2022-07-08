package academy.prog;

import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Random;

class MySuperBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
        var message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();

        try {
            if (message.equals("Make me happy")) {
                sendPhoto(chatId);
            }

            else if (message.equals("Where russian warship must go?")) {
                sendPhoto1(chatId);
            }
            else {
                sendMessage(chatId, "I don't understand!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void sendPhoto(long chatId) throws Exception {
        var name = new Random().nextInt(15);
        var photo = getClass().getClassLoader().getResourceAsStream(name + ".jpg");

        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, "photo"));

        execute(message);
    }

    void sendPhoto1(long chatId) throws Exception {
        var photo1 = getClass().getClassLoader().getResourceAsStream("0.jpg");

        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo1, "photo"));

        execute(message);
    }

    void sendMessage(long chatId, String text) throws Exception {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        execute(message);
    }

    public String getBotUsername() {
        return "WhatabotYoutube_bot";
    }

    public String getBotToken() {
        return "5449215182:AAFnnVKM5Wt0cHfImhL5uChKKrCt3nN8PGQ";
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        var bots = new TelegramBotsApi(DefaultBotSession.class);
        bots.registerBot(new MySuperBot());
    }
}
