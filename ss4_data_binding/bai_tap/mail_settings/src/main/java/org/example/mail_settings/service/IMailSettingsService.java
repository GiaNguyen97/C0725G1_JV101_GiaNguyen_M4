package org.example.mail_settings.service;

import org.example.mail_settings.entity.MailSettings;

public interface IMailSettingsService {
    MailSettings getSettings();

    boolean update(MailSettings newSettings);
}
