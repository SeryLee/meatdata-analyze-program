package com.selab.categoryprogram;

import com.selab.categoryprogram.Service.COMISToCDMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartEventHandler {
    private final COMISToCDMSService comisToCDMSService;
    @EventListener(ApplicationReadyEvent.class)
    public void buildCDMSMetadata() throws IOException {
        comisToCDMSService.classifyCOMIS();
    }
}
