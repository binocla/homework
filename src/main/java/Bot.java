import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.net.ServerSocket;

public class Bot extends TelegramLongPollingBot {
    private static final String PORT = System.getenv("PORT");
    @SneakyThrows
    public void run() {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new Bot());
        try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT))) {
            while (true) {
                serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "Binocladevbot";
    }

    @Override
    public String getBotToken() {
        return "1615035051:AAEUlm3H_7mMGIqt5y0BW8tAYjPdfRKoVwg";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String chat_id = String.valueOf(update.getMessage().getChatId());
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chat_id);
            Message str = update.getMessage();
            switch (str.getText()) {
                case "/bye" -> sendMessage.setText(BotCommands.bye());
                case "/hello" -> sendMessage.setText(BotCommands.hello());
                case "/show" -> sendMessage.setText(String.valueOf(BotCommands.show()));
                default -> sendMessage.setText("Команда не найдена! Напишите /help или /start!");
            }
            if (str.getText().startsWith("/help")  || str.getText().startsWith("/start") ||
                str.getText().startsWith("/reg") || str.getText().startsWith("/del")) {
                sendMessage.setText(BotCommands.proceedCommand(update.getMessage().getFrom(), str.getText()));
            }
            execute(sendMessage);
        }
    }
}
