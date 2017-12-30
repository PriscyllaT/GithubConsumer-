# Github Consumer
App Name: GitHub Profile

[Português]
Consome a API de perfis de usuários do GitHub e as exibe em um app Android.
Na primeira tela é possível fazer uma busca por nome de usuários e, ao clicar em um usuário da lista,
é possível ver o repositório do usuário escolhido.

## Getting Started

Essas instruções vão porpocionar a configuração correta do projeto na sua máquina.

### Prerequisites
Ferramentas utilizadas:

Android Studio 3.0.1

### Installing

Esse projeto utiliza as seguintes bibliotecas e frameworks:

Retrofit 2 e Gson
```
compile 'com.google.code.gson:gson:2.8.2'
compile 'com.squareup.retrofit2:retrofit:2.3.0'
compile 'com.squareup.retrofit2:converter-gson:2.3.0'
```
Dagger  2
```
 compile 'com.google.dagger:dagger:2.5'
 annotationProcessor 'com.google.dagger:dagger-compiler:2.5'
```
Butter Knife
```
 compile 'com.jakewharton:butterknife:8.8.1'
```
Picasso
```
 compile 'com.squareup.picasso:picasso:2.5.2'
```

## Running the tests

O projeto utliza Espresso e JUnit 4.
Para executar os testes vá na pasta test.activities.

## Built With

* [Gradle](https://gradle.org/) 

## Warning
A API do Github possui limites de requisição, podendo não mostrar dados do usuário após esse limite.
https://developer.github.com/v3/#rate-limiting



