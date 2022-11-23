import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot {
    public static final String BOT_TOKEN = "5888248457:AAEfzmb4Qxdp0tHVyWbTEplDEMqMzKqdPcg";

    public static final String BOT_USERNAME = "NasaNetology_bot";

    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=uHnpXohqscz4tEJkjGSvfT8OvaousFC9EGGVh9Wi";

    public MyTelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            switch (update.getMessage().getText()) {
                case "/help":
                    sendMessage("������, � ��� NASA! � ������� ������ �� �������� �� �������. " +
                            "���������, ��� �������� �� ����� NASA ����������� ��� � �����");
                    break;
                case "/give":
                    try {
                        sendMessage(Utils.getUrl(URI));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }

    private void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId("1651191402");
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
