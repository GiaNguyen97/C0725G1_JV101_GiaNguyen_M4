package org.example.mail_settings.repository;

import org.example.mail_settings.entity.MailSettings;

public interface IMailSettingsRepository {

    MailSettings getSettings();

    boolean update(MailSettings newSettings);
}
