package com.example.mailsetting.service;

import com.example.mailsetting.entity.MailSetting;
import com.example.mailsetting.repository.ISettingRepository;
import org.springframework.stereotype.Service;

@Service
public class SettingService implements ISettingService {

    private final ISettingRepository repository;

    public SettingService(ISettingRepository repository) {
        this.repository = repository;
    }

    @Override
    public MailSetting find() {
        MailSetting setting = repository.find();
        return (setting != null) ? setting : new MailSetting();
    }

    @Override
    public MailSetting save(MailSetting settings) {
        return repository.save(settings);
    }
}
