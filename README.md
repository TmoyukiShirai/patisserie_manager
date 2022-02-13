# Name:PatisserieManager
洋菓子店向けの実務サポートアプリケーションです。　　
# DEMO
 [![Image from Gyazo](https://i.gyazo.com/34349346906442ba7141fe15bc96bef8.gif)](https://gyazo.com/34349346906442ba7141fe15bc96bef8)

# Features
- レシピや作業工程を登録し、それに紐づく食材アレルギー情報や単価、メーカーなどを閲覧できます。
- 人数を入力するとエネルギーや原価を計算できます。
- 従業員情報やレシピ・食材の編集は登録した管理者のみが行えます。
- 現段階では未完成ですが、棚卸し表作成、収支計算機能も実装予定です。
- こちらも現段階ではできませんが、工程ページで調理器具と温度、速度と時間を登録することにより、  
ハードウェアと組み合わせて自動調理を行えるようなアプリケーションにできたらと考えております。
# Requirement
- Java(Java11)
- DB（MySQL 8.0）
- AP(Tomcat9)
- Eclipse 2020
- javax.servlet-api (4.0.1)
- mysql-connector-java (8.0.23)
- hibernate-core (5.4.28.Final)
- lombok (1.18.16)
- taglibs-standard-impl (1.2.5)
- javax.servlet.jsp.jstl-api (1.2.1)

# Usage
GitHubからプロジェクトをclone（ダウンロード）します。 
```
$ git clone https://github.com/TmoyukiShirai/p_m.git
```
Eclipseにインポートします。  
Tomcatの構成済みのリソースに追加します。  
以下のファイルの接続タブを表示します。  
　　/patisserie_manager/src/META-INF/persistence.xml  
URL・ユーザー・パスワードをお使いのMySQLの情報に変更し、保存します。
# Author
* Tomoyuki Shirai

