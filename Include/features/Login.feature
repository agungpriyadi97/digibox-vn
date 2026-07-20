#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@LoginProfile
Feature: Login and Account Verification
  Sebagai pengguna yang terdaftar
  Saya ingin masuk ke dalam sistem dengan berbagai kondisi
  Agar saya dapat memastikan sistem merespons kredensial dengan benar

@DataDrivenLogin
  Scenario: Login dengan kredensial dari Global Variable
    When pengguna memasukkan username yang valid
    And pengguna memasukkan password yang valid
    And pengguna menekan tombol login

  Examples: 
    | email                    | password      | status  |
    | agungpriyadi97@gmail.com | Laskar123456@ | success |
    | user_salah@mail.com      | salah123      | fail    |