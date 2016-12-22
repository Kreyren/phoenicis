package com.phoenicis.library;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryConfiguration {
    @Value("${application.user.shortcuts}")
    private String shortcutDirectory;

    @Bean
    public LibraryManager libraryManager() {
        return new LibraryManager(shortcutDirectory);
    }

    @Bean
    public ShortcutCreator shortcutCreator() {
        return new ShortcutCreator(shortcutDirectory, libraryManager());
    }
}