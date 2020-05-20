package com.it.ssm.service;

import com.it.ssm.domain.Syslog;

import java.util.List;

public interface ISyslogService {
    public void save(Syslog syslog);

    public List<Syslog> findAll(Integer page, Integer size);
}
