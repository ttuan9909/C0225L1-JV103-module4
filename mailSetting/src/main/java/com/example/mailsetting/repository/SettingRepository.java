package com.example.mailsetting.repository;

import com.example.mailsetting.entity.MailSetting;
import org.springframework.stereotype.Repository;

@Repository
public class SettingRepository implements ISettingRepository {

    private MailSetting current;

    @Override
    public MailSetting save(MailSetting settings) {
        this.current = settings;
        return settings;
    }

    @Override
    public MailSetting find() {
        return current;
    }
}
