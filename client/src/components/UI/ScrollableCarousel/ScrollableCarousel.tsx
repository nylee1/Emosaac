/** @jsxImportSource @emotion/react */
import { jsx, css } from "@emotion/react";
import React, { useRef, useState, useEffect, useMemo } from "react";
import { throttle } from "lodash";

// import Test from "./Test";
import BookCard from "../BookCard/BookCard";

const ScrollableCarousel = ({ API, identifier }: any) => {
  const wrapperRef = useRef<HTMLInputElement>(null);
  const cardsRef = useRef<any>([]);
  const [bookListData, setBookListData] = useState<object[]>([]);
  type BookList = object | string;
  const [bookListResult, setBookListResult] = useState<BookList[]>([]);
  const [page, setPage] = useState<number>(0);
  const [wrapperWidth, setWrapperWidth] = useState<number>(0);
  const [standard, setStandard] = useState<number>(0);
  const [quantityPerPage, setQuantityPerPage] = useState<number>(10);
  const [loadingTag, setLoadingTag] = useState<string[]>(
    Array(9).fill("LOADING")
  );

  const cardLayout = {
    width: "10vw",
    height: "15vw",
    minWidth: "150px",
    minHeight: "225px",
    padding: "0.5vw",
  };

  const generatePage = (value: number) => {
    if (
      wrapperRef.current !== null &&
      wrapperRef.current.clientWidth !== wrapperWidth
    ) {
      const width = wrapperRef.current.clientWidth;
      const quantity = Math.floor(
        wrapperRef.current.clientWidth / cardsRef.current[0].clientWidth
      );
      const newPage = Math.ceil(standard / quantity);
      setPage(() => newPage);
      setWrapperWidth(() => width);
      setQuantityPerPage(() => quantity);
      return newPage + value;
    } else {
      return page + value;
    }
  };

  const nextBtnClickHandler = () => {
    if (wrapperRef.current !== null) {
      const quantity = Math.floor(
        wrapperRef.current.clientWidth / cardsRef.current[0].clientWidth
      );
      const nextStandard = generatePage(1) * quantity;
      const idx =
        nextStandard < cardsRef.current.length
          ? nextStandard
          : cardsRef.current.length - 1;
      if (nextStandard < cardsRef.current.length) {
        setPage((prev) => prev + 1);
      }
      setStandard(() => idx);
      wrapperRef.current.scrollTo({
        left: cardsRef.current[idx].offsetLeft,
        top: 0,
        behavior: "smooth",
      });
      fetchMoreData();
    }
  };

  const prevBtnClickHandler = () => {
    if (wrapperRef.current !== null) {
      const quantity = Math.floor(
        wrapperRef.current.clientWidth / cardsRef.current[0].clientWidth
      );
      const prevStandard = generatePage(-1) * quantity;
      const idx = prevStandard >= 0 ? prevStandard : 0;
      if (prevStandard >= 0) {
        setPage((prev) => prev - 1);
      }
      setStandard(() => idx);
      wrapperRef.current.scrollTo({
        left: cardsRef.current[idx].offsetLeft,
        top: 0,
        behavior: "smooth",
      });
    }
  };

  const fetchMoreData = () => {
    let standard = 0;
    if (wrapperRef.current !== null && cardsRef.current[0] !== null) {
      standard = Math.ceil(
        wrapperRef.current.scrollLeft / cardsRef.current[0]?.clientWidth
      );
    }
    if (
      (wrapperRef.current !== null &&
        wrapperRef.current.scrollWidth - wrapperRef.current.scrollLeft - 200 <
          wrapperRef.current.clientWidth) ||
      bookListData.length - loadingTag.length - standard <= loadingTag.length
    ) {
      API(bookListData.length, bookListData.length + quantityPerPage + 1).then(
        (res: object[]) => {
          setBookListData((prev) => [...prev, ...res]);
        }
      );
    }
  };

  const onScrollHandler = useMemo(
    () =>
      throttle(() => {
        if (wrapperRef.current !== null) {
          fetchMoreData();
          const standard = Math.ceil(
            wrapperRef.current.scrollLeft / cardsRef.current[0].clientWidth
          );
          setStandard(() => standard);
        }
      }, 300),
    [bookListData, setBookListData]
  );

  useEffect(() => {
    fetchMoreData();
  }, []);

  const generateLoadingData = () => {
    setBookListResult(() => [...bookListData, ...loadingTag]);
  };

  useEffect(() => {
    generateLoadingData();
  }, [bookListData]);

  const renderCards = useMemo(() => bookListResult.map((el, idx) => {
    return (
      <div
        key={`${identifier}-${idx}`}
        ref={(el) => (cardsRef.current[idx] = el)}
        css={cardWrapperCSS({ padding: cardLayout.padding })}
      >
        <BookCard
          bookData={el}
          showPlatform={true}
          width={cardLayout.width}
          height={cardLayout.height}
          minWidth={cardLayout.minWidth}
          minHeight={cardLayout.minHeight}
        />
      </div>
    );
  }), [bookListResult]);

  return (
    <div css={carouselWrapper}>
      <div
        css={prevBtn}
        onClick={prevBtnClickHandler}
        onMouseEnter={(event) => {
          event.stopPropagation();
        }}
      >
        〈
      </div>
      <div
        css={nextBtn}
        onClick={nextBtnClickHandler}
        onMouseEnter={(event) => {
          event.stopPropagation();
        }}
      >
        〉
      </div>
      <div
        ref={wrapperRef}
        css={carousel}
        onWheel={onScrollHandler}
        onTouchMove={onScrollHandler}
      >
        {renderCards}
      </div>
    </div>
  );
};

export default ScrollableCarousel;

const cardWrapperCSS = ({ padding }: { padding: string }) => {
  return css`
    padding-left: ${padding};
    padding-right: ${padding};
  `;
};

const carouselWrapper = css`
  width: 100%;
  position: relative;
`;

const carousel = css`
  display: flex;
  width: 100vw;
  /* padding-left: 48px; */
  box-sizing: border-box;
  overflow-x: scroll;

  &::-webkit-scrollbar {
    display: none;
  }
`;

const prevBtn = css`
  z-index: 999;
  position: absolute;
  left: 0;
  height: 100%;
  display: flex;
  align-items: center;
  font-size: 48px;
  font-weight: 700;
  color: white;
  padding-left: 8px;
  padding-right: 8px;
  background: linear-gradient(to right, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0));
  transition-property: background font-size;
  transition-duration: 0.2s;
  cursor: pointer;
  user-select: none;

  &:hover {
    background: linear-gradient(to right, rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0));
    font-size: 54px;
  }

  @media (max-width: 480px) {
    display: none;
  }
`;

const nextBtn = css`
  z-index: 999;
  position: absolute;
  right: 0;
  height: 100%;
  display: flex;
  align-items: center;
  font-size: 48px;
  font-weight: 700;
  color: white;
  padding-left: 8px;
  padding-right: 8px;
  background: linear-gradient(to left, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0));
  transition-property: background font-size;
  transition-duration: 0.2s;
  cursor: pointer;

  &:hover {
    background: linear-gradient(to left, rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0));
    font-size: 54px;
  }

  @media (max-width: 480px) {
    display: none;
  }
`;
