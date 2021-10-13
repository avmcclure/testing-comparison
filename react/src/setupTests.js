import "@testing-library/jest-dom";
import { server } from "./common/fetchService.spec.js";
import Enzyme from "enzyme";
import Adapter from "enzyme-adapter-react-16";
import chai from "chai";
import chaiEnzyme from "chai-enzyme";

beforeAll(() => server.listen());
afterEach(() => server.resetHandlers());
afterAll(() => server.close());

Enzyme.configure({ adapter: new Adapter() });
chai.use(chaiEnzyme());
