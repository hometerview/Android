# Android 환경에서 Aapple Login 

iOS에서는 Android 환경에서의 Apple 계정 로그인을 제공하는 것에 그렇게 적극적이지 않습니다. 

여타 플랫폼에서 sdk나 api 로직을 제공해주는 것과는 다르게 WebView를 이용해 로그인을 시도해야 합니다. 

그렇기 때문에 아래에서 소개한 방법 중 두번째 방법으로 애플로그인을 구현했습니다. 하지만, 이번 스코프에서 애플로그인은 우리와 함께 가지 못했습니다. 

그렇다면 두가지 방법과 왜 애플로그인을 사용하지 못하게 됐는지에 대해서 설명해보겠습니다. 

### 그전에 애플 개발자 페이지에서 구현해야하는 정보 관련은 다음 링크를 참고하시면 됩니다. 
[링크 1](https://ecsupport.cafe24.com/article/%EC%87%BC%ED%95%91%EB%AA%B0-%EA%B4%80%EB%A6%AC%EC%9E%90/5/2352/?page=3) / 
[링크 2](https://imweb.me/faq?mode=view&category=29&category2=47&idx=71719) / 
[링크 3](https://programmar.tistory.com/41?category=721123)

![SNS Login_App_Apple_Custom](https://user-images.githubusercontent.com/54509842/182092714-699e7a80-65f6-4740-9064-ae5fd44dbca0.png)

기존에 구현하려던 소셜 로그인의 로직은, 클라이언트에서 소셜 로그인 플랫폼에게 받아온 access 토큰을 서버로 전송해서 서버에서 소셜 로그인을 구현했습니다.
하지만 iOS에서 애플로그인을 제공하지 않기 때문에, 자체적으로 WebView를 띄워 애플로그인에 접근해야했습니다. 

* 위에 첨부한 사진을 기준으로 설명해보자면, 1번과 2번 사이에서 자체적으로 CustomWebView를 구성해서 구현해야했습니다. 

	- 본 Branch 기준으로 applelogin 패키지 하위에 이를 구현했습니다. [링크 1](https://github.com/johncodeos-blog/SignInWithAppleAndroidExample/blob/master/app/src/main/java/com/example/signinwithappleexample/MainActivity.kt) / 
[링크 2](https://pg598595.medium.com/sign-in-with-apple-in-android-95de102936b0) / [링크 3](https://github.com/hdtuan87/AppleSignin/blob/master/signinwithapple/src/main/java/tuanhd/libs/signinwithapple/SignInWithAppleService.kt)

	- 하지만, 작동하지 않았습니다. [관련 논쟁](https://github.com/firebase/firebase-js-sdk/issues/4256)
 			
			
![SNS Login_App_Apple](https://user-images.githubusercontent.com/54509842/182092701-8c130230-b015-49c5-a1cb-a87957b1a3e8.png)

그렇기 때문에 생각한 두번째 방식이 바로 중계 서비스(FireBase)를 이용하는 것입니다. 

* 여기서 말한 중계 서비스는 단순히 Authentication 서비스만을 뜻하지 않습니다. Hosting 서비스를 이용해야 합니다.

	- Hosting 서비스란 프로젝트의 고유한 주소(?)를 사용할 수 있게 해주는 서비스입니다. 

	- 그리고 애플로그인에서 이 Hosting 서비스는 애플서버에서 제공하는 애플 로그인 웹뷰를 어디에 띄워줘야하는지 명시적으로 알려줘야하기 때문에 사용하게 됩니다. [이렇게 말이죠](https://boxwitch.tistory.com/entry/%ED%8C%8C%EC%9D%B4%EC%96%B4%EB%B2%A0%EC%9D%B4%EC%8A%A4-firebase-%EC%9B%B9-%ED%98%B8%EC%8A%A4%ED%8C%85-hosting)

	- 이런식으로 본 프로젝트의 애플로그인은 LoginActivity에 구현했습니다. [문서](https://firebase.google.com/docs/auth/android/apple?authuser=0&hl=ko)

![스크린샷 2022-07-26 오후 8 15 20](https://user-images.githubusercontent.com/54509842/182152085-3f914a23-843b-488a-8340-7bad79289a84.png)

이렇게 사용자의 이메일과 정보를 잘 받아옴을 확인할 수 있었죠. (참고 : uid는 파이어베이스 Authentication으로 로그인을 마무리 하고 배정받은 파이어베이스 Auth 고유값입니다.)

### 그럼에도 애플로그인은 이번 스코프에서 빠지게 되었습니다. 왜 이렇게 됐을까요?

- 그 이유는 아이러니하게도 파이어베이스를 씀에 있었습니다. (미리 말씀드리자면 치명적인 문제점이라고 말하기에는 애매한 부분이 있습니다.)

- 파이어베이스의 Authentication을 이용하게 됨으로 access token을 클라이언트로 가지고 올 수 없게 됐습니다. 

- 아키텍처 플로우에서 확인할 수 있듯이 파이어베이스의 Hosting과 Authentication을 사용함으로써, 4단계/5단계에서 우리는 클라이언트에서 필요한 access token을 유실하게 됩니다. 파이어베이스의 Authentication으로 회원가입을 전부 끝내버리기 때문에, 클라이언트에서는 access token을 가지고 있을 겨를이 안 생깁니다. 

- 그렇다면 이것이 무슨 문제를 야기했을까요?

	- 우리는 본 프로젝트를 android와 iOS 두 환경에서 모두 구현하고자 합니다. 그리고 서버에서는 API를 일관적으로 조속히 뽑아내야하는 상황에 있습니다.

	- iOS에서는 첫번째 방식처럼 access token을 충분히 클라이언트단에서 가질 수 있었습니다. 

	- 하지만 android에서는 이것이 현재 불가능한 상태고(다른 방식으로는 애플로그인이 작동함에도 불구하고) API를 access token을 넘겨 구현하기로 한 현재 시점에서는 이번 스코프에서 android에서는 애플로그인을 배제하자고 결정하게 된 것 입니다. 

