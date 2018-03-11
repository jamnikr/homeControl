const React = require('react');
const ReactDOM = require('react-dom');
import Temperature from "./Temperature";

class App extends React.Component {

    constructor(props) {
        super(props);

        const EMPTY_TEMP = {current: '?', avg: '?', min: '?', max: '?'};
        this.state = {
            bathroomTemp: EMPTY_TEMP,
            bedroomTemp: EMPTY_TEMP,
            balconyTemp: EMPTY_TEMP,
            emptyTemp: EMPTY_TEMP
        };
    }

    componentDidMount() {
        this.load();
        setInterval(() => {
            this.load();
        }, 20000);
    }

    load() {
        fetch('/temperature/BATHROOM').then(res => res.json()).then(response => {
            this.setState({bathroomTemp: {current: response.current, avg: response.avg, min: response.min, max: response.max}});
        });

        fetch('/temperature/BEDROOM').then(res => res.json()).then(response => {
            this.setState({bedroomTemp: {current: response.current, avg: response.avg, min: response.min, max: response.max}});
        });

        fetch('/temperature/BALCONY').then(res => res.json()).then(response => {
            this.setState({balconyTemp: {current: response.current, avg: response.avg, min: response.min, max: response.max}});
        });
    }

    render() {
        return (
            <div className="root">
                <div className="line">
                    <SpecialField/>
                    <Temperature room="Łazienka" temperature={this.state.bathroomTemp} color="#EE6B33"/>
                    <Temperature room="Sypialnia" temperature={this.state.bedroomTemp} color="#7ABB57"/>
                    <Temperature room="Balkon" temperature={this.state.balconyTemp} color="#6E99CC"/>
                </div>

                <div className="line">
                    <SpecialField/>
                    <Temperature room="?" temperature={this.state.emptyTemp} color="#769BD0"/>
                    <Temperature room="?" temperature={this.state.emptyTemp} color="#EF982E"/>
                    <Temperature room="?" temperature={this.state.emptyTemp} color="#DE1F26"/>
                </div>
            </div>
        )
    }
}


class SpecialField extends React.Component {
    render() {
        return (
            <div className="special-field">

            </div>
        )
    }
}

ReactDOM.render(
    <App/>
    , document.getElementById('react'));
