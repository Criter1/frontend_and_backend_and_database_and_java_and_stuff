import {useParams, Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import styled from "styled-components";


function FilmPage() {
    const {filmId} = useParams();
    const [data, setData] = useState({});
    const [showBtns, setShowBtns] = useState(false);
    const nav = useNavigate();
    useEffect(() => {
        const f = async () => {
            const response = await axios.get('http://localhost:8080/api/film/' + filmId + '/');
            setData(response.data);
        };
        f();
    }, [filmId]);
    const del = async () => {
        await axios.delete('http://localhost:8080/api/film/' + filmId + '/');
        setTimeout(() => nav('/films/', 500));
    };
    const change = async (event) => {
        const toSend = {}
        for (let elem in data) toSend[elem] = data[elem].toString();
        await axios.post('http://localhost:8080/api/film/', toSend);
    }
    const handleChange = (event) => {
        const b = data
        b[event.target.attributes['tagname'].value] = event.target.value;
        setData({...b});

    };
    const build = () => {
        const rows1 = [];
        for (let elem in data) {
            if (elem != 'id') rows1.push(<div key={elem}>{elem + ': '}<input tagname={elem} onChange={handleChange}
                                                                             type="text"
                                                                             value={data[elem]}/>
            </div>);
        }
        if (rows1.length > 0) {
            if (showBtns == false) setShowBtns(true);
            return rows1;
        } else {
            if (showBtns == true) setShowBtns(false);
            return (<div>Фильм с таким id не найден</div>);
        }
    };
    return (
        <div>
            <Link to={'/films/'}>Назад</Link>
            {build()}
            {showBtns ? <div><DelButton onClick={del} to={'/films/'}>Удалить фильм</DelButton><br/>
                <SendButton onClick={change}>Изменить</SendButton></div> : ''}
        </div>
    );
}

const DelButton = styled.div`
border: black solid 1px;
margin: 10px;
cursor: pointer;
display: inline;
`;
const SendButton = styled.p`
border: black solid 1px;
cursor: pointer;
display: inline;
margin: 10px;
`;

export default FilmPage;
