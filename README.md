# 🐺 ShakalGram

Неофициальный Android-клиент на базе Telegram API и исходников [Telegram для Android](https://github.com/DrKLO/Telegram).

Первая версия использует тёмную тему с янтарными акцентами, отдельную иконку и пакет `space.shakalizator.gram.web`. Действие **«Зашакалить черновик»** доступно по долгому нажатию кнопки отправки. Оно предлагает олбанские замены вроде «Привет, автор» → «Превед, аффтар». После подтверждения текст остаётся в черновике для редактирования или обычной отправки. Ссылки, упоминания и хештеги сохраняются.

Эффект черновика работает на устройстве. Вход, чаты и отправка сообщений используют Telegram API. [Шакализатор для фото, видео, аудио и текста](https://shakalizator.space) доступен отдельно.

## Сборка

Нужны JDK 17, Android SDK 36, Build Tools 36.0.0, NDK 27.2.12479018, CMake 3.22.1 и NASM для x86. Рекомендуется Linux или короткий путь на Windows из-за глубины подмодулей.

```bash
git clone --recurse-submodules --shallow-submodules https://github.com/ChrysNeb/ShakalGram.git
cd ShakalGram
bash ./gradlew :TMessagesProj_AppStandalone:assembleAfatDebug
```

APK при успешной сборке появляется в `TMessagesProj_AppStandalone/build/outputs/apk/`. Для Windows запустите эту же задачу через Android Studio или через `org.gradle.wrapper.GradleWrapperMain` из JAR в `gradle/wrapper`.

Для входа получите собственные `api_id` и `api_hash` в [Telegram API development tools](https://my.telegram.org/apps). Задайте переменные среды `SHAKALGRAM_API_ID` и `SHAKALGRAM_API_HASH` или одноимённые Gradle properties в пользовательском файле Gradle. Не записывайте их в репозиторий. Без них сборка предназначена для проверки интерфейса, а экран входа объясняет настройку.

Для FCM замените `TMessagesProj_AppStandalone/google-services.json` собственной конфигурацией для пакета ShakalGram и передайте `-PSHAKALGRAM_ENABLE_FCM=true`. Для выпуска понадобится собственный ключ подписи.

## Статус

Исходники первого форка. Вход с реальным аккаунтом, пуш-уведомления и обработка медиа в чате ещё не проверены. Отдельные фото-, видео- и аудиоэффекты внутри чата не входят в эту версию.

Сохранена лицензия [GNU GPL v2](LICENSE). Исходная инструкция Telegram находится в [README.upstream.md](README.upstream.md).
