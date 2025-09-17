package com.example.mailsetting.repository;

import com.example.mailsetting.entity.MailSetting;


public interface ISettingRepository {
    MailSetting save(MailSetting settings);
    MailSetting find();
}
