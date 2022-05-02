import styled from "styled-components";
import {useEffect, useState} from "react";
import axios from "axios";
import {Link, useNavigate} from "react-router-dom";


function ClientSearch() {
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
            const response = await axios.get('http://localhost:8080/api/person/');
            setData(response.data);
        };
        resp();
    }, []);
    const filterBuild = () => {
        const rows = [];
        for (let elem in data) if (data[elem].name.toLowerCase().includes(text['name'].toLowerCase())) rows.push(
            <Row key={data[elem].id}
                 to={'/client/' + data[elem].id + '/'}>{JSON.stringify(data[elem]).replaceAll('{', '').replaceAll('}', '').replaceAll(',', ', ').replaceAll('"', '').replaceAll(':', ': ')}</Row>);
        if (rows.length > 0) return rows;
        else return <div>Таким фильтрам не удовлетворяет ни один клиент</div>;
    };
    const add = async () => {
        await axios.post('http://localhost:8080/api/person/', {
            'id': '10005',
            'name': '',
            'phone': '',
            'address': ''
        });
        setTimeout(() => {
            const resp = async () => {
                const response = await axios.get('http://localhost:8080/api/person/');
                setData(response.data);
            };
            resp();
        }, 500);
    }
    return (
        <div>
            {"Поиск по клиентам"}
            <SearchWrapper>
                <FilterWrapper>
                    {'Фильтры:'}<br/>
                    <label>
                        Name:
                        <input type="text" name="name" value={text['name']} onChange={textChange}/><br/>
                        <AddButton onClick={add}>Добавить клиента</AddButton>
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

export default ClientSearch;
