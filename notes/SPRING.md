### Что такое Spring?

- Spring работает по принципу
  инверсии управления (inversion of control, IoC):
  вместо того чтобы приложение
  само контролировало свое выполнение, управление
  передается некоторому
  другому программному обеспечению — в данном
  случае фреймворку Spring
  Посредством системы настроек мы предоставляем
  фреймворку инструкции
  о том, как распоряжаться написанным нами кодом,
  что и определяет логику
  работы приложения. Именно это и подразумевается
  под «инверсией» в аббревиатуре IoC
    - Без  IoC приложение выполняет само себя и использует зависимости по мере необходимости
    - С IoC приложение выполняется в контексте зависимости (фреймворка Spring Core)