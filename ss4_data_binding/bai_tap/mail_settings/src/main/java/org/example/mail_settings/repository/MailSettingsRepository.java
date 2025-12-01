package org.example.mail_settings.repository;

import org.example.mail_settings.entity.MailSettings;
import org.springframework.stereotype.Repository;

@Repository
public class MailSettingsRepository implements IMailSettingsRepository {
    private static MailSettings settings = new MailSettings(
            "English",
            25,
            false,
            "Thor\nKing, Asgard"
    );

    @Override
    public MailSettings getSettings() {
        return settings;
    }

    @Override
    public boolean update(MailSettings newSettings) {
        settings = newSettings;
        return true;
    }
}
