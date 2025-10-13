INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760338735355_오징어_모임통장_시즌2_약관', '예적금', '오징어 모임통장 시즌2 약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760338786627_오징어_모임통장_시즌2_상품설명서', '예적금', '오징어 모임통장 시즌2 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760338835061_롯데자이언츠_승리기원적금_약관', '예적금', '롯데자이언츠 승리기원적금 약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760338888247_롯데자이언츠_승리기원적금_상품설명서', '예적금', '롯데자이언츠 승리기원적금 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760339362772_매일출석적금_상품설명서', '예적금', '매일출석적금 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760339416598_적립식예금약관', '예적금', '적립식예금약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760339437717_거치식예금약관', '예적금', '거치식예금약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760343319692_아기천사적금_약관', '예적금', '아기천사적금 약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760343363994_아기천사적금_상품설명서', '예적금', '아기천사적금 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760343414194_아이사랑적금_약관', '예적금', '아이사랑적금 약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760343436721_아이사랑적금_상품설명서', '예적금', '아이사랑적금 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760343501520_예금거래기본약관', '예적금', '예금거래기본약관');


-- 아이사랑적금 약관/상품설명서
UPDATE DEPOSIT_PRODUCT SET TERM_ID = 10, MANUAL_ID = 11 WHERE PRODUCT_ID = 6;

-- 아기천사적금 약관/상품설명서
UPDATE DEPOSIT_PRODUCT SET TERM_ID = 8, MANUAL_ID = 9 WHERE PRODUCT_ID = 7;

-- 오징어 모임통장 약관/상품설명서
UPDATE DEPOSIT_PRODUCT SET TERM_ID = 1, MANUAL_ID = 2 WHERE PRODUCT_ID = 61;

-- 롯데자이언츠 승리기원적금 약관/상품설명서
UPDATE DEPOSIT_PRODUCT SET TERM_ID = 3, MANUAL_ID = 4 WHERE PRODUCT_ID = 62;

-- 매일출석적금 약관/상품설명서
UPDATE DEPOSIT_PRODUCT SET TERM_ID = 6, MANUAL_ID = 5 WHERE PRODUCT_ID = 63;

COMMIT;

---------


INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760388689563_신용카드_개인회원_표준약관', '카드', '신용카드 개인회원 표준약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760388738453_신용카드_기업회원_약관', '카드', '신용카드 기업회원 약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'T', '1760388814504_체크카드_개인회원_표준약관', '카드', '체크카드 개인회원 표준약관');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760388893884_부산_동백전_체크카드_상품설명서', '카드', '부산 동백전 체크카드 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760388945627_캐쉬백카드_상품설명서', '카드', '캐쉬백카드 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760389008895_BNK_프렌즈_신용카드_상품설명서', '카드', 'BNK 프렌즈 신용카드 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760389070021_BNK_부자되세요_홈쇼핑카드_상품설명서', '카드', 'BNK 부자되세요 홈쇼핑카드 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760389107278_딩딩_신용카드_상품설명서', '카드', '딩딩 신용카드 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760389221500_REX2_대한항공마일리지형_개인_상품설명서', '카드', 'REX2_대한항공마일리지형_개인 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760389272646_팟_카드_상품설명서', '카드', '팟 카드 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760389356392_2030_언택트_체크카드_상품설명서', '카드', '2030 언택트 체크카드 상품설명서');

INSERT INTO bnk_document (moddate, regdate, document_type, filename, product_type, title) VALUES
(SYSTIMESTAMP, SYSTIMESTAMP, 'M', '1760389384750_SOHO_BIZ카드_상품설명서', '카드', 'SOHO-BIZ카드 상품설명서');

-- 부산 동백전 체크카드 약관/상품설명서
UPDATE CARD SET TERM_ID = 15, MANUAL_ID = 16 WHERE CARD_ID = 1;

-- 캐쉬백카드 약관/상품설명서
UPDATE CARD SET TERM_ID = 13, MANUAL_ID = 17 WHERE CARD_ID = 2;

-- BNK 프렌즈 신용카드 약관/상품설명서
UPDATE CARD SET TERM_ID = 13, MANUAL_ID = 18 WHERE CARD_ID = 3;

-- BNK 부자되세요 홈쇼핑카드 약관/상품설명서
UPDATE CARD SET TERM_ID = 13, MANUAL_ID = 19 WHERE CARD_ID = 4;

-- 딩딩 신용카드 약관/상품설명서
UPDATE CARD SET TERM_ID = 13, MANUAL_ID = 20 WHERE CARD_ID = 5;

-- REX2_대한항공마일리지형(개인) 약관/상품설명서
UPDATE CARD SET TERM_ID = 13, MANUAL_ID = 21 WHERE CARD_ID = 6;

-- 팟(pod) 카드 약관/상품설명서
UPDATE CARD SET TERM_ID = 13, MANUAL_ID = 22 WHERE CARD_ID = 12;

-- 2030 언택트 체크카드 약관/상품설명서
UPDATE CARD SET TERM_ID = 15, MANUAL_ID = 23 WHERE CARD_ID = 14;

-- SOHO-BIZ카드 약관/상품설명서
UPDATE CARD SET TERM_ID = 14, MANUAL_ID = 24 WHERE CARD_ID = 22;

COMMIT;