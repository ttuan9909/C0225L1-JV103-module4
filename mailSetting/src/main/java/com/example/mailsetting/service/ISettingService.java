package com.example.mailsetting.service;

import com.example.mailsetting.entity.MailSetting;

public interface ISettingService {
    MailSetting save(MailSetting settings);
    MailSetting find();
}
