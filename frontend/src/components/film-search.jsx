import styled from "styled-components";
import {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";


function FilmSearch() {
    const [text, setText] = useState({'name': '', 'author': '', 'year': '', 'company': ''});
    const [data, setData] = useState({});
    const textChange = (e) => setText(p => {
        const t = {...p};
        t[e.target.name] = e.target.value;
        return t;
    });
    const nav = useNavigate();
    useEffect(() => {
        const resp = async () => {
            const response = await axios.get('http://localhost:8080/api/film/');
            setData(response.data);
        };
        resp();
    }, []);
    const filterBuild = () => {
        const rows = [];
        for (let elem in data) if (data[elem].name.toLowerCase().includes(text['name'].toLowerCase()) && data[elem].author.toLowerCase().includes(text['author'].toLowerCase()) && data[elem].year.toString().includes(text['year'].toLowerCase()) && data[elem].company.toLowerCase().includes(text['company'].toLowerCase())) rows.push(
            <Row key={data[elem].id}
                 to={'/film/' + data[elem].id + '/'}>{JSON.stringify(data[elem]).replaceAll('{', '').replaceAll('}', '').replaceAll(',', ', ').replaceAll('"', '').replaceAll(':', ': ')}</Row>);
        if (rows.length > 0) return rows;
        else return <div>Таким фильтрам не удовлетворяет ни один фильм</div>;
    };
    const add = async () => {
        await axios.post('http://localhost:8080/api/film/', {
            'id': '10005',
            'year': '0',
            'author': '',
            'name': '',
            'disk_price': '0',
            'company': '',
            'cassete_price': '0'
        });
        setTimeout(() => {
            const resp = async () => {
                const response = await axios.get('http://localhost:8080/api/film/');
                setData(response.data);
            };
            resp();
        }, 500);
    }
    return (
        <div>
            {"Поиск по фильмам"}
            <SearchWrapper>
                <FilterWrapper>
                    {'Фильтры:'}<br/>
                    <label>
                        Name:
                        <input type="text" name="name" value={text['name']} onChange={textChange}/><br/>
                        Author:
                        <input type="text" name="author" value={text['author']} onChange={textChange}/><br/>
                        Year:
                        <input type="text" name="year" value={text['year']} onChange={textChange}/><br/>
                        Company:
                        <input type="text" name="company" value={text['company']} onChange={textChange}/><br/>
                        <AddButton onClick={add}>Добавить фильм</AddButton>
                    </label>
                </FilterWrapper>
                <OutputWrapper>{filterBuild()}</OutputWrapper>
            </SearchWrapper>
        </div>
    );
}

const FilterWrapper = styled.div`
  width: 30%;
  border: 1px blue solid;
  padding: 10px;
`;
const OutputWrapper = styled.div`
  width: 70%;
  border: 1px blue solid;
  padding: 10px;
`;
const SearchWrapper = styled.div`
  display: flex;
`;
const Row = styled(Link)`
  border: 1px blue solid;
  display: flex;
  padding: 10px;
`;
const AddButton = styled.div`
  border: 1px blue solid;
  cursor: pointer;
  display: inline;
`;

export default FilmSearch;
