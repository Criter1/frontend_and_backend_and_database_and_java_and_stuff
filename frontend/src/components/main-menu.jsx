import styled from "styled-components";
import {Link} from "react-router-dom";

function MainMenu() {
    return (
        <MainMenuWrapper>
            {"Главное меню"}<br/>
            <Link to={'/films'}>{'Поиск по фильмам'}</Link><br/>
            <Link to={'/clients'}>{'Поиск по клиентам'}</Link>
        </MainMenuWrapper>
    );
}

const MainMenuWrapper = styled.div`
  align-content: center;
  margin: 10px;
`;

export default MainMenu;
