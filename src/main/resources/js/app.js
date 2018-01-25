const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {bathroomTemp: null, bedroomTemp: null, balconyTemp: null};
    }

    componentDidMount() {
        this.load();
        setInterval(() => {
            this.load();
        }, 20000);
    }

    load() {
        fetch('/temperature/BATHROOM').then(res => res.json()).then(response => {
            this.setState({bathroomTemp: response});
        });

        fetch('/temperature/BEDROOM').then(res => res.json()).then(response => {
            this.setState({bedroomTemp: response});
        });

        fetch('/temperature/BALCONY').then(res => res.json()).then(response => {
            this.setState({balconyTemp: response});
        });
    }

    render() {
        return (
            <div>
                <Temperature room="Łazienka" temperature={this.state.bathroomTemp}/>
                <Temperature room="Sypialnia" temperature={this.state.bedroomTemp}/>
                <Temperature room="Balkon" temperature={this.state.balconyTemp}/>
            </div>
        )
    }
}

class Temperature extends React.Component {
    render() {
        return (
            <div className="box">
                <div className="picture"><img src="/img/weather.jpg"/></div>
                <div className="temperature">{this.props.temperature}&#176;</div>
                <div className="room">{this.props.room}</div>
                <div className="bottom"></div>
            </div>
        )
    }
}

ReactDOM.render(<App/>, document.getElementById('react'));
