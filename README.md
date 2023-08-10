# batch_template
SpringBoot V-2.x와 V-3.x에서의 batch작성 예시

• Spring batch의 메타 테이블 구조

![domain1](./images/domain1.png)

( 출처: [metaDataSchema](https://docs.spring.io/spring-batch/3.0.x/reference/html/metaDataSchema.html) )
관련 sql문 위치 : spring-batch-core-(version).jar 안에 위치해 있다. (schema-라는 파일명을 검색하면 쉽게 찾을 수 있다.)

이 테이블들이 있어야만 Spring Batch가 정상 작동한다.

