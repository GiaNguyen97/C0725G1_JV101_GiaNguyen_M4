package org.example.mail_settings.service;

import org.example.mail_settings.repository.IMailSettingsRepository;
import org.springframework.stereotype.Service;

@Service
public class MailSettingsService implements IMailSettingsService {
    private IMailSettingsRepository mailSettingsRepository;

    public MailSettingsService(IMailSettingsRepository mailSettingsRepository) {
        this.mailSettingsRepository = mailSettingsRepository;
    }

    @Override
    public boolean update(org.example.mail_settings.entity.MailSettings newSettings) {
        return mailSettingsRepository.update(newSettings);
    }

    @Override
    public org.example.mail_settings.entity.MailSettings getSettings() {
        return mailSettingsRepository.getSettings();
    }
}
