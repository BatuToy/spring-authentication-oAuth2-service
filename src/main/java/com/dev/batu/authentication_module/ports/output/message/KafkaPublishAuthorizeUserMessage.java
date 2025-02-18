package com.dev.batu.authentication_module.ports.output.message;

import com.dev.batu.authentication_module.common.event.Message;
import com.dev.batu.authentication_module.domain.event.user.UserAuthorizedEvent;

public interface KafkaPublishAuthorizeUserMessage extends Message<UserAuthorizedEvent> {
    void send(UserAuthorizedEvent event, String topic);
}
