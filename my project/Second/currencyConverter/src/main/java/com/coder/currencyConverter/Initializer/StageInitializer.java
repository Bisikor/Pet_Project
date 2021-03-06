package com.coder.currencyConverter.Initializer;

import com.coder.currencyConverter.ChartApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<ChartApplication.StageReadyEvent> {
    @Value("classpath:/CurrencyFX.fxml")
    private Resource chartResource;
    private final String applicationTitle;
    private ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle
            , ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ChartApplication.StageReadyEvent event) throws RuntimeException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());
            //fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 540, 630));
            stage.setMaxHeight(540);stage.setMaxWidth(630);
            stage.setMinHeight(540);stage.setMinWidth(630);
            stage.setTitle(applicationTitle);
            stage.getIcons().add(new Image("LastIconsConvecor.png"));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
